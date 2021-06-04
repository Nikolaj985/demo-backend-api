package com.nikgri.demospringpizzaapi.pizza.controller;

import com.nikgri.demospringpizzaapi.pizza.model.PizzaDto;
import com.nikgri.demospringpizzaapi.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizza")
@CrossOrigin(origins = {"https://mydemo-frontend-pizza.herokuapp.com", "http://localhost:4200"})
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public List<PizzaDto> getAllPizzas() {
        return this.pizzaService.getAllPizzas();
    }

    @GetMapping("/{name}")
    public PizzaDto getPizzaByName(@PathVariable String name) {
        return this.pizzaService.findByName(name);
    }

    @PostMapping("/new")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> newPizza(@RequestBody PizzaDto pizza) {
        System.out.println(pizza);
        return this.pizzaService.addNewPizza(pizza);
    }


    @PutMapping()
    public ResponseEntity activatePizza(@RequestBody String name) {
        return this.pizzaService.activatePizza(name);
    }

    @PutMapping("/edit")
    public ResponseEntity activatePizza(@RequestBody PizzaDto pizzaDto) {
        return this.pizzaService.editPizza(pizzaDto);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity deletePizza(@PathVariable String name) {
        return this.pizzaService.deletePizza(name);
    }

}
