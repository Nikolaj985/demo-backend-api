package com.nikgri.demospringpizzaapi.pizza.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Pizza with that name already exist!")
public class PizzaAlreadyExistException extends RuntimeException{

    public PizzaAlreadyExistException() {
        super();
    }
}
