package com.algaworks.algamoneyapi.service;

import com.algaworks.algamoneyapi.model.Entry;
import com.algaworks.algamoneyapi.model.Person;
import com.algaworks.algamoneyapi.repository.EntryRepository;
import com.algaworks.algamoneyapi.repository.PersonRepository;
import com.algaworks.algamoneyapi.service.exception.PersonNotExistsOrIsInactiveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntryService {

    @Autowired
    private PersonRepository personService;

    @Autowired
    private EntryRepository entryRepository;

    public Entry save(Entry entry) {

        Optional<Person> personEntity = personService.findById(entry.getPerson().getId());
        if (!personEntity.isPresent() || personEntity.get().isInactive()) {
            throw new PersonNotExistsOrIsInactiveException();
        }

        return entryRepository.save(entry);
    }
}
