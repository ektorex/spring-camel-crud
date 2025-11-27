
package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("personController")
public class PersonController {

    @Autowired
    private PersonRepository repo;

    // GET /api/persons
    public List<Person> list() {
        return repo.findAll();  // ✔️ Camel puede serializar List<Person>
    }

    // POST /api/persons
    public Person create(Person p) {
        return repo.save(p);    // ✔️ retorna JSON válido
    }

    // GET /api/persons/{id}
    public Person get(Long id) {
        return repo.findById(id).orElse(null); // ✔️ Camel serializa null sin problema
    }

    // PUT /api/persons/{id}
    public Person update(Long id, Person p) {
        p.setId(id);
        return repo.save(p);
    }

    // DELETE /api/persons/{id}
    public String delete(Long id) {
        repo.deleteById(id);
        return "deleted"; // ✔️ String simple, serializable
    }
}
