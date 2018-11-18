package com.algaworks.algamoneyapi.repository.entry;

import com.algaworks.algamoneyapi.model.Entry;
import com.algaworks.algamoneyapi.repository.filter.EntryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EntryRepositoryQuery {

    public Page<Entry> filter(EntryFilter entryFilter, Pageable pageable);

}
