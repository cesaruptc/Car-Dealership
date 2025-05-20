package com.uptc.frw.cardealership.controller;

import com.uptc.frw.cardealership.model.Person;
import com.uptc.frw.cardealership.model.Vehicle;
import com.uptc.frw.cardealership.service.PersonService;
import com.uptc.frw.cardealership.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/getAll")
    public List<Vehicle> getAllVehicle() {
        return vehicleService.getAllVehicles();
    }

    @PostMapping
    public Vehicle saveVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.saveVehicle(vehicle);
    }
}
