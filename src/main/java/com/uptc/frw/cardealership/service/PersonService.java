package com.uptc.frw.cardealership.service;

import com.uptc.frw.cardealership.model.Person;
import com.uptc.frw.cardealership.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPersonById(long personId) {
        return personRepository.findById(personId).orElse(null);
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public void deletePerson(long personId){
        Person person = personRepository.findById(personId).orElse(null);

        if (person == null) {
            throw new RuntimeException("No se encontró persona con ID: " + personId);
        } else {
            personRepository.delete(person);
        }
    }

    public Person updatePerson(Person newPerson){
        Person person = getPersonById(newPerson.getId());
        person.setName(newPerson.getName());
        person.setIdentification(newPerson.getIdentification());
        person.setAddress(newPerson.getAddress());
        person.setPhone(newPerson.getPhone());
        return personRepository.save(person);
    }
}
