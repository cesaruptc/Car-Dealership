package com.uptc.frw.cardealership.service;

import com.uptc.frw.cardealership.model.*;
import com.uptc.frw.cardealership.repository.VehicleOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VehicleOptionService {

    @Autowired
    private VehicleOptionRepository vehicleOptionRepository;
    @Autowired
    private OptionService optionService;
    @Autowired
    private VehicleService vehicleService;

    public List<VehicleOption> getAllVehicleOptions() {
        return vehicleOptionRepository.findAll();
    }

    public VehicleOption getVehicleOptionById(long vehicleOptionId) {
        return vehicleOptionRepository.findById(vehicleOptionId).orElse(null);
    }

    public VehicleOption saveVehicleOption(VehicleOption vehicleOption) {
        vehicleOption.setPrice(vehicleOption.getPrice());

        Option option = optionService.getOptionById(vehicleOption.getOptionId());
        if (option == null) {
            throw new RuntimeException("Opción no encontrada con ID: " + vehicleOption.getOptionId());
        }
        vehicleOption.setOption(option);

        Vehicle vehicle = vehicleService.getVehicleById(vehicleOption.getVehicleId());
        if (vehicle == null) {
            throw new RuntimeException("Vehículo no encontrado con ID: " + vehicleOption.getVehicleId());
        }
        vehicleOption.setVehicle(vehicle);

        return vehicleOptionRepository.save(vehicleOption);
    }

    public void deleteVehicleOption(long vehicleOptionId) {
        if (getVehicleOptionById(vehicleOptionId) == null) {
            throw new RuntimeException("No se encontró una opción_vehiculo con ID: " + vehicleOptionId);
        } else {
            VehicleOption vehicleOption = vehicleOptionRepository.findById(vehicleOptionId).orElse(null);
            vehicleOptionRepository.delete(vehicleOption);
        }
    }
    public VehicleOption updateVehicleOption(VehicleOption newVehicleOption){
        VehicleOption vehicleOption = getVehicleOptionById(newVehicleOption.getId());
        Option option = optionService.getOptionById(newVehicleOption.getOptionId());
        if (option == null) {
            throw new RuntimeException("No se encontró "+ newVehicleOption.getOptionId());
        }
        vehicleOption.setOption(option);
        Vehicle vehicle = vehicleService.getVehicleById(newVehicleOption.getVehicleId());
        if (vehicle == null) {
            throw new RuntimeException(newVehicleOption.getVehicleId() + " no encontrado");
        }
        vehicleOption.setVehicle(vehicle);
        return vehicleOptionRepository.save(vehicleOption);
    }

}
