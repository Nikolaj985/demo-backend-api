package com.nikgri.demospringpizzaapi.pizza.model;

public enum EHeat {
    MILD(1),
    HOT(2),
    SPICY(3);

    public final int value;

    EHeat(int value) {
        this.value = value;
    }
}
