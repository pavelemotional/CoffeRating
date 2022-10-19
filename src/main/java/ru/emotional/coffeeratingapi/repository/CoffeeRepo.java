package ru.emotional.coffeeratingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.emotional.coffeeratingapi.entity.Coffee;

@Repository
public interface CoffeeRepo extends JpaRepository<Coffee, Long> {
}
