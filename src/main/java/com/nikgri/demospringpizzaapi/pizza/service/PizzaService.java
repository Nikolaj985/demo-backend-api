package com.nikgri.demospringpizzaapi.pizza.service;

import com.nikgri.demospringpizzaapi.pizza.entity.Pizza;
import com.nikgri.demospringpizzaapi.pizza.exceptions.InvalidPizzaNameException;
import com.nikgri.demospringpizzaapi.pizza.exceptions.PizzaAlreadyExistException;
import com.nikgri.demospringpizzaapi.pizza.model.PizzaDto;
import com.nikgri.demospringpizzaapi.pizza.repository.PizzaRepository;
import com.nikgri.demospringpizzaapi.security.payload.response.MessageResponse;
import com.nikgri.demospringpizzaapi.topping.entity.Topping;
import com.nikgri.demospringpizzaapi.topping.model.ToppingDto;
import com.nikgri.demospringpizzaapi.topping.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final ToppingRepository toppingRepository;


    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, ToppingRepository toppingRepository) {
        this.pizzaRepository = pizzaRepository;
        this.toppingRepository = toppingRepository;
    }

    public List<PizzaDto> getAllPizzas() {
        List<Pizza> pizzas;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
            pizzas = this.pizzaRepository.findAll().stream().filter(Pizza::isActive).collect(Collectors.toList());
        } else {
            pizzas = this.pizzaRepository.findAll();
        }

        List<Topping> toppings = this.toppingRepository.findAll();
        List<PizzaDto> pizzaDto = new ArrayList<>();

        for (Pizza pizza : pizzas) {
            List<ToppingDto> toppingDto = new ArrayList<>();
            for (Topping topping : toppings) {
                boolean contains = false;
                for (Pizza toppingPizza : topping.getPizzas()) {
                    if (toppingPizza.getId().equals(pizza.getId())) {
                        contains = true;
                    }
                }
                if (contains) {
                    toppingDto.add(new ToppingDto(topping.getId(), topping.getDescription()));
                }
            }
            pizzaDto.add(new PizzaDto(pizza.getId(), pizza.getName(), pizza.getPrice(),
                    pizza.getImage(), pizza.getHeat(), pizza.getSalePrice(), pizza.isActive(), toppingDto));
        }
        return pizzaDto;
    }

    public PizzaDto findByName(String name) {
        try {
            Pizza pizza = this.pizzaRepository.findByName(name);
            List<Topping> toppings = this.toppingRepository.findAll();
            List<ToppingDto> toppingDto = new ArrayList<>();
            for (Topping topping : toppings) {
                boolean contains = false;
                for (Pizza toppingPizza : topping.getPizzas()) {
                    if (toppingPizza.getId().equals(pizza.getId())) {
                        contains = true;
                    }
                }
                if (contains) {
                    toppingDto.add(new ToppingDto(topping.getId(), topping.getDescription()));
                }
            }
            return new PizzaDto(pizza.getId(), pizza.getName(), pizza.getPrice(), pizza.getImage(),
                    pizza.getHeat(), pizza.getSalePrice(), pizza.isActive(), toppingDto);
        } catch (NullPointerException e) {

            throw new InvalidPizzaNameException("No pizza with such name found!");

        }
    }

    public ResponseEntity<Object> addNewPizza(PizzaDto pizza) {
        if (!this.pizzaRepository.existsByName(pizza.getName())) {
            System.out.println(pizza.getToppings().stream().findFirst().get().getDescription());
            Set<Topping> toppings = new HashSet<>();

            for (ToppingDto toppingDto : pizza.getToppings()) {
                if (this.toppingRepository.existsByDescription(toppingDto.getDescription())) {
                    toppings.add(this.toppingRepository.findByDescription(toppingDto.getDescription()));
                } else {
                    toppings.add(new Topping(toppingDto.getId(), toppingDto.getDescription()));
                }
            }

            Pizza pizzaToSave = new Pizza(pizza.getId(), pizza.getName(), pizza.getPrice(), pizza.getImage(),
                    pizza.getHeat(), pizza.getSalePrice(), true, toppings);
            this.pizzaRepository.save(pizzaToSave);
            return ResponseEntity.ok(new MessageResponse("Pizza added!"));
        } else {
            throw new PizzaAlreadyExistException();
        }
    }

    public ResponseEntity deletePizza(String name) {
        if (this.pizzaRepository.existsByName(name)) {
            Pizza tempPizza = this.pizzaRepository.findByName(name);
            tempPizza.setActive(false);
            this.pizzaRepository.save(tempPizza);
            return ResponseEntity.ok(new MessageResponse("Pizza deactivated!"));
        } else {
            throw new InvalidPizzaNameException("No pizza with that name found!");
        }

    }

    public ResponseEntity activatePizza(String name) {
        if (this.pizzaRepository.existsByName(name)) {
            Pizza tempPizza = this.pizzaRepository.findByName(name);
            tempPizza.setActive(true);
            this.pizzaRepository.save(tempPizza);
            return ResponseEntity.ok(new MessageResponse("Pizza activated!"));
        } else {
            throw new InvalidPizzaNameException("No pizza with that name found!");
        }

    }

    public ResponseEntity editPizza(PizzaDto pizzaDto) {
        if (this.pizzaRepository.existsByName(pizzaDto.getName())) {
            Pizza tempPizza = this.pizzaRepository.findByName(pizzaDto.getName());
            tempPizza.setImage(pizzaDto.getImage());
            tempPizza.setHeat(pizzaDto.getHeat());
            tempPizza.setPrice(pizzaDto.getPrice());
            tempPizza.setSalePrice(pizzaDto.getSalePrice());

            Set<Topping> tempToppings = new HashSet<>();

            for (ToppingDto toppingDto : pizzaDto.getToppings()) {
                if (this.toppingRepository.existsByDescription(toppingDto.getDescription())) {
                    tempToppings.add(this.toppingRepository.findByDescription(toppingDto.getDescription()));
                } else {
                    tempToppings.add(new Topping(toppingDto.getId(), toppingDto.getDescription()));
                }
            }
            tempPizza.setToppings(tempToppings);
            this.pizzaRepository.save(tempPizza);
            return ResponseEntity.ok(new MessageResponse("Pizza edited!"));
        } else {
            throw new InvalidPizzaNameException("No pizza with that name found!");
        }

    }

}
