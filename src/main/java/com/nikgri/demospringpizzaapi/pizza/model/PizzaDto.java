package com.nikgri.demospringpizzaapi.pizza.model;

import com.nikgri.demospringpizzaapi.topping.model.ToppingDto;

import java.util.List;

public class PizzaDto {
    private int id;
    private String name;
    private int price;
    private String image;
    private int salePrice;
    private boolean isActive;
    private List<ToppingDto> toppings;

    public PizzaDto(int id, String name, int price, String image, int salePrice, boolean isActive, List<ToppingDto> toppings) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.salePrice = salePrice;
        this.isActive = isActive;
        this.toppings = toppings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<ToppingDto> getToppings() {
        return toppings;
    }

    public void setToppings(List<ToppingDto> toppings) {
        this.toppings = toppings;
    }
}
