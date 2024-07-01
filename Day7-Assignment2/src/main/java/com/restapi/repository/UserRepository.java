package com.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	User findByUsernameAndPassword(String username,String password);

	User findByEmail(String email);

	User findByUsernameOrEmail(String username,String email);
}
