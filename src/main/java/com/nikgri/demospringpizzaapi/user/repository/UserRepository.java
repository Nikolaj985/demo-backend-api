package com.nikgri.demospringpizzaapi.user.repository;

import com.nikgri.demospringpizzaapi.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
