package com.uptc.frw.cardealership.controller;

import com.uptc.frw.cardealership.model.Option;
import com.uptc.frw.cardealership.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("options")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @GetMapping("/getAll")
    public List<Option> getAllOptions() {
        return optionService.getAllOptions();
    }

    @GetMapping("/option/{optionId}")
    public Option getOptionByID (@PathVariable long optionId) {
        return optionService.getOptionById(optionId);
    }

    @PostMapping
    public Option saveOption(@RequestBody Option option) {
        return optionService.saveOption(option);
    }

    @DeleteMapping
    public void deleteOption( @RequestParam long optionId){
        optionService.deleteOption(optionId);
    }

    @PutMapping
    public Option updateOption(@RequestBody Option newOption){
        return optionService.updateOption(newOption);
    }
}

