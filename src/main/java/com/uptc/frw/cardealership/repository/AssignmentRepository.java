package com.uptc.frw.cardealership.repository;

import com.uptc.frw.cardealership.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByPersonId(long sellerId);
}
