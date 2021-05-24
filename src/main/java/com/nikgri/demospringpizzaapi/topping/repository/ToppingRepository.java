package com.nikgri.demospringpizzaapi.topping.repository;

import com.nikgri.demospringpizzaapi.topping.entity.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingRepository extends JpaRepository<Topping, Integer> {
    Topping findByDescription(String description);

    boolean existsByDescription(String description);
    //boolean existsById(Integer id);

}
