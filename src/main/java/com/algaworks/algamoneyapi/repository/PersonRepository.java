package com.algaworks.algamoneyapi.repository;

import com.algaworks.algamoneyapi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
