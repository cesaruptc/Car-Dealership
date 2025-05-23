package com.uptc.frw.cardealership.controller;

import com.uptc.frw.cardealership.model.Person;
import com.uptc.frw.cardealership.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/getAll")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/person/{personId}")
    public Person getPersonById(@PathVariable long personId) {
        return personService.getPersonById(personId);
    }

    @PostMapping
    public Person savePerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    @DeleteMapping
    public void deletePerson( @RequestParam long personId){
        personService.deletePerson(personId);
    }

    @PutMapping
    public Person updatePerson(@RequestBody Person newPerson){
        return personService.updatePerson(newPerson);
    }
}
