package com.algaworks.algamoneyapi.resource;

import com.algaworks.algamoneyapi.event.CreateResourceEvent;
import com.algaworks.algamoneyapi.exceptionhandler.AlgamoneyExceptionHandler;
import com.algaworks.algamoneyapi.model.Entry;
import com.algaworks.algamoneyapi.repository.EntryRepository;
import com.algaworks.algamoneyapi.repository.filter.EntryFilter;
import com.algaworks.algamoneyapi.service.EntryService;
import com.algaworks.algamoneyapi.service.exception.PersonNotExistsOrIsInactiveException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entries")
public class EntryResource {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private EntryService entryService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public Page<Entry> list(EntryFilter entryFilter, Pageable pageable) {
        return entryRepository.filter(entryFilter, pageable);
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
        Entry entity = entryService.save(entry);
        publisher.publishEvent(new CreateResourceEvent(this, response, entity.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        entryRepository.deleteById(id);
    }

    @ExceptionHandler({PersonNotExistsOrIsInactiveException.class})
    public ResponseEntity<Object> handlePersonNotExistsOrIsInactiveException(PersonNotExistsOrIsInactiveException ex) {
        String messageUser = messageSource.getMessage("person.not_exists_or_inactive", null, LocaleContextHolder.getLocale());
        String messageDev = ex.toString();

        List<AlgamoneyExceptionHandler.Error> errors = Arrays.asList(new AlgamoneyExceptionHandler.Error(messageUser, messageDev));

        return ResponseEntity.badRequest().body(errors);
    }
}
