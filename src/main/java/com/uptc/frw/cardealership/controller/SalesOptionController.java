package com.uptc.frw.cardealership.controller;

import com.uptc.frw.cardealership.model.Assignment;
import com.uptc.frw.cardealership.model.SalesOption;
import com.uptc.frw.cardealership.service.SalesOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sales-options")
public class SalesOptionController {

    @Autowired
    private SalesOptionService salesOptionService;

    @GetMapping("/getAll")
    public List<SalesOption> getAllSalesOptions() {
        return salesOptionService.getAllSalesOptions();
    }

    @GetMapping("/sales-option/{salesOptionsId}")
    public SalesOption getSalesOptionById(@PathVariable long salesOptionsId) {
        return salesOptionService.getSalesOptionById(salesOptionsId);
    }

    @PostMapping
    public SalesOption saveSalesOption(@RequestBody SalesOption salesOption) {
        return salesOptionService.saveSalesOption(salesOption);
    }

    @DeleteMapping
    public void deleteAssignment(@RequestParam long assignmentId) {
        //
    }
}
