package org.example.personalfinanceapp.repository;

import org.example.personalfinanceapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
