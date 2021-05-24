package com.nikgri.demospringpizzaapi.topping.service;

import com.nikgri.demospringpizzaapi.topping.entity.Topping;
import com.nikgri.demospringpizzaapi.topping.exceptions.ToppingAlreadyExistException;
import com.nikgri.demospringpizzaapi.topping.model.ToppingDto;
import com.nikgri.demospringpizzaapi.topping.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToppingService {

    private final ToppingRepository toppingRepository;

    @Autowired
    public ToppingService(ToppingRepository toppingRepository) {
        this.toppingRepository = toppingRepository;
    }

    public List<Topping> getAlLToppings() {
        return this.toppingRepository.findAll();
    }

    public ResponseEntity<Object> deleteToppingById(int id) {
        if (this.toppingRepository.existsById(id)) {
            this.toppingRepository.deleteById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Topping not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> addNewTopping(ToppingDto toppingDto) {

        if (!this.toppingRepository.existsByDescription(toppingDto.getDescription())) {
            this.toppingRepository.save(new Topping(toppingDto.getDescription()));
            return new ResponseEntity<>("Topping added!", HttpStatus.OK);
        } else {
            throw new ToppingAlreadyExistException();
        }
    }

}
