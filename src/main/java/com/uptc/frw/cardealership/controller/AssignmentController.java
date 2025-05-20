package com.uptc.frw.cardealership.controller;

import com.uptc.frw.cardealership.model.Assignment;
import com.uptc.frw.cardealership.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping("/getAll")
    public List<Assignment> getAllAssignments() {
        return assignmentService.getAllAssignments();
    }

    @GetMapping("/assigment/{assignmentId}")
    public Assignment getAssignmentById(@PathVariable long assignmentId) {
        return assignmentService.getAssignmentById(assignmentId);
    }

    @PostMapping
    public Assignment saveAssignment(@RequestBody Assignment assignment) {
        return assignmentService.saveAssignment(assignment);
    }

    @DeleteMapping
    public void deleteAssignment(@RequestParam long assignmentId) {
        assignmentService.deleteAssignment(assignmentId);
    }
}
