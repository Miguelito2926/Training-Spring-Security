package com.ednaldo.springsecurity6.repositories;

import com.ednaldo.springsecurity6.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
