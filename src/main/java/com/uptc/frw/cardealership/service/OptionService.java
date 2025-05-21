package com.uptc.frw.cardealership.service;

import com.uptc.frw.cardealership.model.Option;
import com.uptc.frw.cardealership.repository.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionService {
    @Autowired
    private OptionRepository optionRepository;

    public List<Option> getAllOptions() {
        return optionRepository.findAll();
    }

    public Option getOptionById(long optionId) {
        return optionRepository.findById(optionId).orElse(null);
    }

    public Option saveOption(Option option) {
        return optionRepository.save(option);
    }

    public void deleteOption(long optionId){
        //
    }

}
