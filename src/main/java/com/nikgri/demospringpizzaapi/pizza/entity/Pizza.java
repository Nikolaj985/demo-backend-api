package com.nikgri.demospringpizzaapi.pizza.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nikgri.demospringpizzaapi.topping.entity.Topping;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "pizzas")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;


    @Column(name = "price")
    private int price;

    @Column(name = "image")
    private String image;

    @Column(name = "sale_price")
    private int salePrice;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonIgnore
    @JoinTable(name = "pizzas_toppings",
            joinColumns = @JoinColumn(name = "id_pizzas", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_toppings", referencedColumnName = "id"))
    private Set<Topping> toppings;

    public Pizza() {
    }

    public Pizza(String name, int price, String image, int salePrice, boolean isActive, Set<Topping> toppings) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.salePrice = salePrice;
        this.isActive = isActive;
        this.toppings = toppings;
    }

    public Pizza(Integer id, String name, int price, String image, int salePrice, boolean isActive, Set<Topping> toppings) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.salePrice = salePrice;
        this.isActive = isActive;
        this.toppings = toppings;
    }

    public Integer getId() {
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

    public Set<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(Set<Topping> toppings) {
        this.toppings = toppings;
    }
}
