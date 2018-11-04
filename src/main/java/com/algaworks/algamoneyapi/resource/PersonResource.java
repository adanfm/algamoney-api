package com.algaworks.algamoneyapi.resource;

import com.algaworks.algamoneyapi.event.CreateResourceEvent;
import com.algaworks.algamoneyapi.model.Person;
import com.algaworks.algamoneyapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonResource {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Person> list() {
        return personRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response) {
        Person entity = personRepository.save(person);

        publisher.publishEvent(new CreateResourceEvent(this, response, entity.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> find(@PathVariable Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.isPresent()
            ? ResponseEntity.ok(person.get())
            : ResponseEntity.notFound().build()
        ;

    }
}
