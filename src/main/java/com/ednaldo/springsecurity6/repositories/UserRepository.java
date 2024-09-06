package com.ednaldo.springsecurity6.repositories;

import com.ednaldo.springsecurity6.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
