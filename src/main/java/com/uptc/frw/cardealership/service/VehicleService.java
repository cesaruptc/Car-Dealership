package com.uptc.frw.cardealership.service;

import com.uptc.frw.cardealership.model.Vehicle;
import com.uptc.frw.cardealership.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(long vehicleId) {
        return vehicleRepository.findById(vehicleId).orElse(null);
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(long vehicleId){
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);

        if (vehicle == null) {
            throw new RuntimeException("No se encontró vehiculo con ID: " + vehicleId);
        } else {
            vehicleRepository.delete(vehicle);
        }
    }

    public Vehicle updateVehicle(Vehicle newVehicle){
        Vehicle vehicle = getVehicleById(newVehicle.getId());
        vehicle.setBrand(newVehicle.getBrand());
        vehicle.setModel(newVehicle.getModel());
        vehicle.setEngineCapacity(newVehicle.getEngineCapacity());
        vehicle.setPrice(newVehicle.getPrice());
        vehicle.setType(newVehicle.getType());
        return vehicleRepository.save(vehicle);
    }
}
