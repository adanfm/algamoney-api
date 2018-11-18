package com.algaworks.algamoneyapi.repository;

import com.algaworks.algamoneyapi.model.Entry;
import com.algaworks.algamoneyapi.repository.entry.EntryRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long>, EntryRepositoryQuery {
}
