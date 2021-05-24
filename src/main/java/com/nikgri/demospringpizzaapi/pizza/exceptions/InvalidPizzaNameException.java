package com.nikgri.demospringpizzaapi.pizza.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidPizzaNameException extends RuntimeException {
    public InvalidPizzaNameException(String message) {
        super(message);
        // return(message);
    }
}
