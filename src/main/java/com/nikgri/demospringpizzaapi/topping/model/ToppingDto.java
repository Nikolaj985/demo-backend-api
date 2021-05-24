package com.nikgri.demospringpizzaapi.topping.model;

import com.nikgri.demospringpizzaapi.pizza.model.PizzaDto;

import java.util.Set;

public class ToppingDto {

    private int id;
    private String description;

    public ToppingDto(int id, String description) {
        this.id = id;
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

}
