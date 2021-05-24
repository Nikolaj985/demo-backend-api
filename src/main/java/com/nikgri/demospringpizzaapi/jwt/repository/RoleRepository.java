package com.nikgri.demospringpizzaapi.jwt.repository;

import com.nikgri.demospringpizzaapi.jwt.models.ERole;
import com.nikgri.demospringpizzaapi.jwt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
