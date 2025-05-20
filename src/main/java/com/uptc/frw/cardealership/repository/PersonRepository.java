package com.uptc.frw.cardealership.repository;

import com.uptc.frw.cardealership.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
