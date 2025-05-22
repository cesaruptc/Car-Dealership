package com.uptc.frw.cardealership.controller;

import com.uptc.frw.cardealership.model.Assignment;
import com.uptc.frw.cardealership.model.VehicleOption;
import com.uptc.frw.cardealership.service.VehicleOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vehicle-options")
public class VehicleOptionController {

    @Autowired
    private VehicleOptionService vehicleOptionService;

    @GetMapping("/getAll")
    public List<VehicleOption> getAllVehicleOptions() {
        return vehicleOptionService.getAllVehicleOptions();
    }

    @GetMapping("/vehicle-option/{vehicleOptionId}")
    public VehicleOption getAssignmentById(@PathVariable long vehicleOptionId) {
        return vehicleOptionService.getVehicleOptionById(vehicleOptionId);
    }

    @PostMapping
    public VehicleOption saveVehicleOption(@RequestBody VehicleOption vehicleOption) {
        return vehicleOptionService.saveVehicleOption(vehicleOption);
    }

    @DeleteMapping
    public void deleteVehicleOption(@RequestParam long vehicleOptionId) {
        //
    }
    @PutMapping
    public VehicleOption updateVehicleOption(@RequestBody VehicleOption newVehicleOption){
        return vehicleOptionService.updateVehicleOption(newVehicleOption);
    }
}
