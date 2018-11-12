package com.algaworks.algamoneyapi.service;

import com.algaworks.algamoneyapi.model.Person;
import com.algaworks.algamoneyapi.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person update(Long id, Person person) {
        Optional<Person> personEntity = getPerson(id);

        Person personSave = personEntity.get();

        BeanUtils.copyProperties(person, personSave, "id");
        return personRepository.save(personSave);
    }

    private Optional<Person> getPerson(Long id) {
        Optional<Person> personEntity = personRepository.findById(id);

        if (personEntity.isPresent() == false) {
            throw new EmptyResultDataAccessException(1);
        }
        return personEntity;
    }

    public void updatePropertyActive(Long id, Boolean active) {
        Optional<Person> personEntity = getPerson(id);

        Person personSave = personEntity.get();

        personSave.setActive(active);
        personRepository.save(personSave);
    }
}
