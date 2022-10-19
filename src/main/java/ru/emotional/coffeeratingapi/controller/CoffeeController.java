package ru.emotional.coffeeratingapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.emotional.coffeeratingapi.entity.Coffee;
import ru.emotional.coffeeratingapi.repository.CoffeeRepo;
import ru.emotional.coffeeratingapi.service.CoffeeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = {"/api/v1"})
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;


    @GetMapping("/{id}")
    public ResponseEntity<Coffee> one(@PathVariable Long id){
        Optional<Coffee> coffee = coffeeService.findById(id);
        if(coffee.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(coffee.get());
    }


    @GetMapping("/all")
    public ResponseEntity<List<Coffee>> all(){
        return ResponseEntity.ok(coffeeService.findAll());
    }


}
