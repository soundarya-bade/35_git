package com.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
