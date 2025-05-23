package com.uptc.frw.cardealership.service;

import com.uptc.frw.cardealership.model.*;
import com.uptc.frw.cardealership.repository.SalesOptionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@RequiredArgsConstructor
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
    public SalesOption updateSalesOption(SalesOption newSalesOption){
        SalesOption salesOption = getSalesOptionById(newSalesOption.getId());
        Sale sale = saleService.getSaleById(newSalesOption.getSaleId());
        if (sale == null) {
            throw new RuntimeException("Venta no encontrada con ID: " + newSalesOption.getSaleId());
        }
        salesOption.setSales(sale);
        VehicleOption vehicleOption = vehicleOptionService.getVehicleOptionById(newSalesOption.getVehicleOptionId());
        if (vehicleOption == null) {
            throw new RuntimeException("Opcion_Vehiculo no encontrado con ID: " + newSalesOption.getVehicleOptionId());
        }
        salesOption.setVehicleOptionId(newSalesOption.getVehicleOptionId());
        return salesOptionsRepository.save(salesOption);
    }






}
