package com.uptc.frw.cardealership.service;

import com.uptc.frw.cardealership.model.*;
import com.uptc.frw.cardealership.repository.SalesOptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SalesOptionService {

    @Autowired
    private SalesOptionsRepository salesOptionsRepository;

    @Autowired
    private SaleService saleService;

    @Autowired
    private VehicleOptionService vehicleOptionService;


    public List<SalesOption> getAllSalesOptions() {
        return salesOptionsRepository.findAll();
    }

    public SalesOption getSalesOptionById(long salesOptionId) {
        return salesOptionsRepository.findById(salesOptionId).orElse(null);
    }

    public SalesOption saveSalesOption(SalesOption salesOption) {
        Sale sale = saleService.getSaleById(salesOption.getSaleId());
        if (sale == null) {
            throw new RuntimeException("Venta no encontrada con ID: " + salesOption.getSaleId());
        }
        salesOption.setSales(sale);

        VehicleOption vehicleOption = vehicleOptionService.getVehicleOptionById(salesOption.getVehicleOptionId());
        if (vehicleOption == null) {
            throw new RuntimeException("Opcion_Vehiculo no encontrado con ID: " + salesOption.getVehicleOptionId());
        }
        salesOption.setVehicleOption(vehicleOption);

        return salesOptionsRepository.save(salesOption);
    }

    public void deleteSalesOption(long salesOptionId) {
        if (getSalesOptionById(salesOptionId) == null) {
            throw new RuntimeException("No se encontró sales option con ID: " + salesOptionId);
        } else {
            SalesOption salesOption = salesOptionsRepository.findById(salesOptionId).orElse(null);
            salesOptionsRepository.delete(salesOption);
        }
    }

}
