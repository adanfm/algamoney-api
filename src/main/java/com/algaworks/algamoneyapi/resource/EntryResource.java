package com.algaworks.algamoneyapi.resource;

import com.algaworks.algamoneyapi.event.CreateResourceEvent;
import com.algaworks.algamoneyapi.model.Entry;
import com.algaworks.algamoneyapi.repository.EntryRepository;
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
@RequestMapping("/entries")
public class EntryResource {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Entry> list() {
        return entryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entry> find(@PathVariable Long id) {
        Optional<Entry> entry = entryRepository.findById(id);
        return entry.isPresent()
            ? ResponseEntity.ok(entry.get())
            : ResponseEntity.notFound().build()
        ;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Entry> create(@Valid @RequestBody Entry entry, HttpServletResponse response) {
        Entry entity = entryRepository.save(entry);
        publisher.publishEvent(new CreateResourceEvent(this, response, entity.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }
}
