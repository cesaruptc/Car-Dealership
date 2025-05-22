package com.uptc.frw.cardealership.service;

import com.uptc.frw.cardealership.model.Assignment;
import com.uptc.frw.cardealership.model.Person;
import com.uptc.frw.cardealership.model.Sale;
import com.uptc.frw.cardealership.model.Vehicle;
import com.uptc.frw.cardealership.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@RequiredArgsConstructor
@Service
public class SaleService {

    @Autowired
    private PersonService personService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private AssignmentService assignmentService;
    @Autowired
    private SaleRepository saleRepository;


    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(long saleId) {
        return saleRepository.findById(saleId).orElse(null);
    }

    public Sale saveSale(Sale sale) {
        sale.setNewLicense(sale.getNewLicense());
        sale.setSaleDate(new Date());
        sale.setPrice(sale.getPrice());

        Person client = personService.getPersonById(sale.getClientId());
        if (client == null) {
            throw new RuntimeException("Persona no encontrada con ID: " + sale.getClientId());
        }
        sale.setClient(client);

        Person seller = personService.getPersonById(sale.getSellerId());
        if (seller == null) {
            throw new RuntimeException("Persona no encontrada con ID: " + sale.getSellerId());
        }
        sale.setSeller(seller);

        Vehicle vehicle = vehicleService.getVehicleById(sale.getVehicleId());
        if (vehicle == null) {
            throw new RuntimeException("Vehículo no encontrado con ID: " + sale.getVehicleId());
        }
        sale.setVehicle(vehicle);

        Assignment assignment = assignmentService.getAssignmentById(sale.getAssignmentId());
        if (assignment == null) {
            throw new RuntimeException("Asignación no encontrada con ID: " + sale.getAssignmentId());
        }
        sale.setAssignment(assignment);

        return saleRepository.save(sale);
    }

    public void deleteSale(long saleId) {
        if (getSaleById(saleId) == null) {
            throw new RuntimeException("No se encontró una venta con ID: " + saleId);
        } else {
            Sale sale = saleRepository.findById(saleId).orElse(null);
            saleRepository.delete(sale);
        }
    }
    public Sale updateSale(Sale newSale){
        Sale sale = getSaleById(newSale.getId());
        sale.setNewLicense(newSale.getNewLicense());
        sale.setSaleDate(newSale.getSaleDate());
        sale.setPrice(newSale.getPrice());
        Person seller = personService.getPersonById(newSale.getSellerId());
        if (seller == null) {
            throw new RuntimeException("Persona no encontrada con ID: " + newSale.getSellerId());
        }
        sale.setSeller(seller);

        Vehicle vehicle = vehicleService.getVehicleById(newSale.getVehicleId());
        if (vehicle == null) {
            throw new RuntimeException("Vehículo no encontrado con ID: " + newSale.getVehicleId());
        }
        sale.setVehicle(vehicle);

        Assignment assignment = assignmentService.getAssignmentById(newSale.getAssignmentId());
        if (assignment == null) {
            throw new RuntimeException("Asignación no encontrada con ID: " + newSale.getAssignmentId());
        }
        sale.setAssignment(assignment);
        return saleRepository.save(sale);
    }

}
