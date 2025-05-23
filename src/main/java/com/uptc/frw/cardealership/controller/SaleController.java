package com.uptc.frw.cardealership.controller;

import com.uptc.frw.cardealership.model.Assignment;
import com.uptc.frw.cardealership.model.Sale;
import com.uptc.frw.cardealership.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping("/getAll")
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/sale/{saleId}")
    public Sale getSaleById(@PathVariable long saleId) {
        return saleService.getSaleById(saleId);
    }

    @GetMapping("seller/{sellerId}")
    public List<Sale> getSalesBySellerId(@PathVariable long sellerId) {
        return saleService.getSalesBySellerId(sellerId);
    }

    @PostMapping
    public Sale saveSale(@RequestBody Sale sale) {
        return saleService.saveSale(sale);
    }

    @DeleteMapping
    public void deleteSale(@RequestParam long saleId) {
        saleService.deleteSale(saleId);
    }

    @PutMapping
    public Sale updateSale(@RequestBody Sale newSale){
        return saleService.updateSale(newSale);
    }
}
