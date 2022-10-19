package ru.emotional.coffeeratingapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.emotional.coffeeratingapi.entity.Coffee;
import ru.emotional.coffeeratingapi.repository.CoffeeRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepo coffeeRepo;
    public List<Coffee> findAll() {
        return coffeeRepo.findAll();
    }
    public Optional<Coffee> findById(Long id) {
        return coffeeRepo.findById(id);
    }
}
