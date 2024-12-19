package com.service.backend.repository;

import com.service.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom query to find a user by email
    User findByEmail(String email);
}
