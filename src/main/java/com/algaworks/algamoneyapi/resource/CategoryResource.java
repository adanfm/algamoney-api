package com.algaworks.algamoneyapi.resource;

import com.algaworks.algamoneyapi.model.Category;
import com.algaworks.algamoneyapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> list() {
        return categoryRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category entity = categoryRepository.save(category);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequestUri()
            .path("/{id}")
            .buildAndExpand(entity.getId()).toUri()
        ;

        return ResponseEntity.created(uri).body(entity);
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
