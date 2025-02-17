package com.service.goodsteward.repository;

import com.service.goodsteward.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // Custom query to find a user by email
    User findByEmail(String email);
}
