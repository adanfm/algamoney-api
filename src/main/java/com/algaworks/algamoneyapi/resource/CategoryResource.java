package com.algaworks.algamoneyapi.resource;

import com.algaworks.algamoneyapi.event.CreateResourceEvent;
import com.algaworks.algamoneyapi.model.Category;
import com.algaworks.algamoneyapi.repository.CategoryRepository;
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
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Category> list() {
        return categoryRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Category> create(@Valid @RequestBody Category category, HttpServletResponse response) {
        Category entity = categoryRepository.save(category);

        publisher.publishEvent(new CreateResourceEvent(this, response, entity.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> find(@PathVariable Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.isPresent()
            ? ResponseEntity.ok(category.get())
            : ResponseEntity.notFound().build()
        ;

    }
}
