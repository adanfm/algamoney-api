package com.algaworks.algamoneyapi.repository.entry;

import com.algaworks.algamoneyapi.model.Entry;
import com.algaworks.algamoneyapi.repository.filter.EntryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EntryRepositoryImpl implements EntryRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public Page<Entry> filter(EntryFilter entryFilter, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Entry> criteria = builder.createQuery(Entry.class);

        Root<Entry> root = criteria.from(Entry.class);

        // WHERE's
        Predicate[] predicates = createRestrictions(entryFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Entry> query = entityManager.createQuery(criteria);
        addRestrictionPagination(query, pageable);

        return new PageImpl(query.getResultList(), pageable, total(entryFilter));
    }

    private Predicate[] createRestrictions(EntryFilter entryFilter, CriteriaBuilder builder, Root<Entry> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(entryFilter.getDescription())) {
            predicates.add(builder.like(
                builder.lower(root.get("description")), "%" + entryFilter.getDescription().toLowerCase() + "%"
            ));
        }

        if (entryFilter.getDateDueBegin() != null) {
            predicates.add(builder.greaterThanOrEqualTo(
                root.get("dateDue"), entryFilter.getDateDueBegin()
            ));
        }

        if (entryFilter.getDateDueUntil() != null) {
            predicates.add(builder.lessThanOrEqualTo(
                root.get("dateDue"), entryFilter.getDateDueUntil()
            ));
        }

        return predicates.toArray(new Predicate[predicates.size()]);

    }

    private void addRestrictionPagination(TypedQuery<Entry> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalResults = pageable.getPageSize();
        int firstResultPage = currentPage * totalResults;

        query.setFirstResult(firstResultPage);
        query.setMaxResults(totalResults);
    }

    private long total(EntryFilter entryFilter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Entry> root = criteriaQuery.from(Entry.class);

        Predicate[] predicates = createRestrictions(entryFilter, builder, root);
        criteriaQuery.where(predicates);

        criteriaQuery.select(builder.count(root));
        return entityManager.createQuery(criteriaQuery).getSingleResult();

    }
}
