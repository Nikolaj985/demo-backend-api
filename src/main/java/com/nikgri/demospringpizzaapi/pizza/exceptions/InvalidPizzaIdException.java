package com.nikgri.demospringpizzaapi.pizza.exceptions;

public class InvalidPizzaIdException extends NullPointerException {

    public InvalidPizzaIdException(String s) {
        super(s);
    }
}
