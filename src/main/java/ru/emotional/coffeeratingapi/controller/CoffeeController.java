package ru.emotional.coffeeratingapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.emotional.coffeeratingapi.collectors_coffee.SbmrneCollector;
import ru.emotional.coffeeratingapi.collectors_coffee.TastyCoffeeCollector;
import ru.emotional.coffeeratingapi.entity.Coffee;
import ru.emotional.coffeeratingapi.service.CoffeeService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = {"/api/v1"})
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;


    @GetMapping("/{id}")
    public ResponseEntity<Coffee> one(@PathVariable Long id) {
        Optional<Coffee> coffee = coffeeService.findById(id);
        if (coffee.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(coffee.get());

    }

    @GetMapping("/add")
    public ResponseEntity<Coffee> add(@RequestParam String link) {

        Coffee coffee = null;

        try {
            coffee = SbmrneCollector.getCoffee(link);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }

        coffeeService.save(coffee);
        return ResponseEntity.ok(coffee);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Coffee>> all() {
        return ResponseEntity.ok(coffeeService.findAll());
    }

    @GetMapping("/addall")
    public ResponseEntity<List<Coffee>> addAll() {
        List<String> allLinksTasty = TastyCoffeeCollector.getAllLinks();
        List<String> allLinksSbmrn = SbmrneCollector.getAllLinks();

        allLinksTasty.forEach(link -> {
            try {
                Coffee coffee = TastyCoffeeCollector.getCoffee(link);
                coffeeService.save(coffee);
            } catch (Exception e) {
                System.out.println(link + "\n" + e.getMessage());
            }
        });

        allLinksSbmrn.forEach(link -> {
            try {
                Coffee coffee = SbmrneCollector.getCoffee(link);
                coffeeService.save(coffee);
            } catch (Exception e) {
                System.out.println(link + "\n" + e.getMessage());
            }
        });


        return ResponseEntity.ok(coffeeService.findAll());
    }


}
