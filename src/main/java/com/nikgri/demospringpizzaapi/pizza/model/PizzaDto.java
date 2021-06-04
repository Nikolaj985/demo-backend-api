package com.nikgri.demospringpizzaapi.pizza.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nikgri.demospringpizzaapi.topping.model.ToppingDto;

import java.util.List;

public class PizzaDto {
    private int id;
    private String name;
    private double price;
    private String image;
    private EHeat heat;
    private double salePrice;

    @JsonProperty(value = "isActive")
    private boolean isActive;
    private List<ToppingDto> toppings;

    public PizzaDto() {
    }

    public PizzaDto(int id, String name, double price, String image, EHeat heat, double salePrice, boolean isActive, List<ToppingDto> toppings) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.heat = heat;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public EHeat getHeat() {
        return heat;
    }

    public void setHeat(EHeat heat) {
        this.heat = heat;
    }

    public List<ToppingDto> getToppings() {
        return toppings;
    }

    public void setToppings(List<ToppingDto> toppings) {
        this.toppings = toppings;
    }

    @Override
    public String toString() {
        return "PizzaDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", heat=" + heat +
                ", salePrice=" + salePrice +
                ", isActive=" + isActive +
                ", toppings=" + toppings +
                '}';
    }
}
