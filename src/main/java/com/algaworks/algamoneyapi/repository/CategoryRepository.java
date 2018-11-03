package com.algaworks.algamoneyapi.repository;

import com.algaworks.algamoneyapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
