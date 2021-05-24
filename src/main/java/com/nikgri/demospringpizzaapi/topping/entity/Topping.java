package com.nikgri.demospringpizzaapi.topping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nikgri.demospringpizzaapi.pizza.entity.Pizza;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "toppings")
public class Topping {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="description")
    private String description;

    @ManyToMany(mappedBy = "toppings")
    @JsonIgnore
    private Set<Pizza> pizzas;

    public Topping() {
    }

    public Topping(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Topping(int id, String description, Set<Pizza> pizzas) {
        this.id = id;
        this.description = description;
        this.pizzas = pizzas;
    }

    public Topping(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(Set<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}
