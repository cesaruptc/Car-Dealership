package com.uptc.frw.cardealership.service;

import com.uptc.frw.cardealership.model.Assignment;
import com.uptc.frw.cardealership.model.Person;
import com.uptc.frw.cardealership.model.Vehicle;
import com.uptc.frw.cardealership.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private PersonService personService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private AssignmentRepository assignmentRepository;


    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public Assignment getAssignmentById(long assignmentId) {
        return assignmentRepository.findById(assignmentId).orElse(null);
    }

    public Assignment saveAssignment(Assignment assignment) {
        assignment.setDate(new Date());
        assignment.setPriceAppraisal(assignment.getPriceAppraisal());

        Person person = personService.getPersonById(assignment.getPersonId());
        if (person == null) {
            throw new RuntimeException("Persona no encontrada con ID: " + assignment.getPersonId());
        }
        assignment.setPerson(person);

        Vehicle vehicle = vehicleService.getVehicleById(assignment.getVehicleId());
        if (vehicle == null) {
            throw new RuntimeException("Vehículo no encontrado con ID: " + assignment.getVehicleId());
        }
        assignment.setVehicle(vehicle);

        return assignmentRepository.save(assignment);
    }

    public void deleteAssignment(long assignmentId) {
        if (getAssignmentById(assignmentId) == null) {
            throw new RuntimeException("No se encontró una compra con ID: " + assignmentId);
        } else {
            Assignment assignment = assignmentRepository.findById(assignmentId).orElse(null);
            assignmentRepository.delete(assignment);
        }
    }

}
