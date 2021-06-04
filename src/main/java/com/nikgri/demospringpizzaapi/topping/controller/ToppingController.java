package com.nikgri.demospringpizzaapi.topping.controller;

import com.nikgri.demospringpizzaapi.topping.entity.Topping;
import com.nikgri.demospringpizzaapi.topping.model.ToppingDto;
import com.nikgri.demospringpizzaapi.topping.service.ToppingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("permitAll()")
    @GetMapping
    public List<Topping> getAllToppings() {
        return this.toppingService.getAlLToppings();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        return this.toppingService.deleteToppingById(id);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/new")
    public ResponseEntity<Object> newTopping(@RequestBody ToppingDto topping) {
        return this.toppingService.addNewTopping(topping);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/name/{description}")
    public ResponseEntity<Object> deleteToppingByName(@PathVariable String description) {
        return this.toppingService.deleteToppingByName(description);
    }
}
