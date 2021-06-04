package com.nikgri.demospringpizzaapi.security.repository;

import com.nikgri.demospringpizzaapi.security.models.ERole;
import com.nikgri.demospringpizzaapi.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
