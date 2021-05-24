package com.nikgri.demospringpizzaapi.pizza.service;

import com.nikgri.demospringpizzaapi.pizza.entity.Pizza;
import com.nikgri.demospringpizzaapi.pizza.exceptions.InvalidPizzaIdException;
import com.nikgri.demospringpizzaapi.pizza.exceptions.InvalidPizzaNameException;
import com.nikgri.demospringpizzaapi.pizza.exceptions.PizzaAlreadyExistException;
import com.nikgri.demospringpizzaapi.pizza.model.PizzaDto;
import com.nikgri.demospringpizzaapi.pizza.repository.PizzaRepository;
import com.nikgri.demospringpizzaapi.topping.entity.Topping;
import com.nikgri.demospringpizzaapi.topping.model.ToppingDto;
import com.nikgri.demospringpizzaapi.topping.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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



    public List<PizzaDto> getAllPizzas(){
        List<Pizza> pizzas = this.pizzaRepository.findAll();
        List<Topping> toppings = this.toppingRepository.findAll();

        List<PizzaDto> pizzaDto = new ArrayList<>();
        for(Pizza pizza : pizzas){
            List<ToppingDto> toppingDto = new ArrayList<>();
            for (Topping topping: toppings){
                boolean contains = false;
                for(Pizza pizza1: topping.getPizzas()){
                    if(pizza1.getId().equals(pizza.getId())){
                        contains = true;
                    }
                }
                if(contains){
                    toppingDto.add(new ToppingDto(topping.getId(),topping.getDescription()));
                }
            }
            pizzaDto.add(new PizzaDto(pizza.getId(),pizza.getName(),pizza.getPrice(),pizza.getImage(),pizza.getSalePrice(), pizza.isActive(),toppingDto));
        }

        return pizzaDto;
    }

    public PizzaDto findByName(String name){
        try{
            Pizza pizza =  this.pizzaRepository.findByName(name);
            List<Topping> toppings = this.toppingRepository.findAll();
            List<ToppingDto> toppingDto = new ArrayList<>();
            for (Topping topping: toppings){
                boolean contains = false;
                for(Pizza pizza1: topping.getPizzas()){
                    if(pizza1.getId().equals(pizza.getId())){
                        contains = true;
                    }
                }
                if(contains){
                    toppingDto.add(new ToppingDto(topping.getId(),topping.getDescription()));
                }
            }
            return  new PizzaDto(pizza.getId(),pizza.getName(),pizza.getPrice(),pizza.getImage(),pizza.getSalePrice(),pizza.isActive(),toppingDto);
        }
        catch (NullPointerException e){

         throw new InvalidPizzaNameException("No pizza with such name found!");

        }
    }

    public ResponseEntity<Object> addNewPizza(PizzaDto pizza){
        if (!this.pizzaRepository.existsByName(pizza.getName())){
            System.out.println(pizza.getToppings().stream().findFirst().get().getDescription());
            Set<Topping> toppings = new HashSet<>();

            for(ToppingDto toppingDto : pizza.getToppings()){
                if(this.toppingRepository.existsByDescription(toppingDto.getDescription())){
                    toppings.add(this.toppingRepository.findByDescription(toppingDto.getDescription()));
                }else{
                    toppings.add(new Topping(toppingDto.getId(), toppingDto.getDescription()));
                }
                this.toppingRepository.saveAll(toppings);
            }

            Pizza pizzaToSave= new Pizza(pizza.getId(),pizza.getName(),pizza.getPrice(),pizza.getImage(),pizza.getSalePrice(),pizza.isActive(),toppings);
            this.pizzaRepository.save(pizzaToSave);
            return new ResponseEntity<>("Added", HttpStatus.OK);
        }else{
            throw new PizzaAlreadyExistException();
        }
    }

    public void deletePizza(int id){
        if(this.pizzaRepository.existsById(id)){
            this.pizzaRepository.deleteById(id);
        }else{
            throw new InvalidPizzaIdException("No pizza with that Id found!");
        }

    }
}
