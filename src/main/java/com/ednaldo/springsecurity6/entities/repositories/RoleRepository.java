package com.ednaldo.springsecurity6.entities.repositories;

import com.ednaldo.springsecurity6.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
