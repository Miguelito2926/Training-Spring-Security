package com.ednaldo.springsecurity6.repositories;

import com.ednaldo.springsecurity6.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Set<Role> findByName(String nameRole);
}
