package com.scm.scm20.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.scm20.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
}
