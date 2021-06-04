package com.nikgri.demospringpizzaapi.topping.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Topping not found!")
public class ToppingNotFoundException extends RuntimeException {
    public ToppingNotFoundException() {
        super();
    }
}
