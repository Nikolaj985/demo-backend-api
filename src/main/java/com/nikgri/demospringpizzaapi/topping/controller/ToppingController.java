package com.nikgri.demospringpizzaapi.topping.controller;

import com.nikgri.demospringpizzaapi.topping.entity.Topping;
import com.nikgri.demospringpizzaapi.topping.service.ToppingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topping")
@CrossOrigin(origins = {"https://mydemo-frontend-pizza.herokuapp.com", "http://localhost:4200"})
public class ToppingController {

    private ToppingService toppingService;

    public ToppingController(ToppingService toppingService) {
        this.toppingService = toppingService;
    }

    @GetMapping
    public List<Topping> getAllToppings() {
        return this.toppingService.getAlLToppings();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        return this.toppingService.deleteToppingById(id);
    }

}
