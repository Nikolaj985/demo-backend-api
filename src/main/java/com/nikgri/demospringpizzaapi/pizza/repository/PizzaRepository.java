package com.nikgri.demospringpizzaapi.pizza.repository;

import com.nikgri.demospringpizzaapi.pizza.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    Pizza findByName(String name);

    boolean existsByName(String name);
}
