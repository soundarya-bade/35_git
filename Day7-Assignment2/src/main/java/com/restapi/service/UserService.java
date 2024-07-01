package com.restapi.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.entities.User;
import com.restapi.exception.ResourceNotFoundException;
import com.restapi.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UserService {

	@Autowired
	UserRepository userRepository;

	public void createUser(User user) {
		log.info("User Service:: createUser {} {}",user.getId(),user.getEmail());
		userRepository.save(user);
		log.info("User saved successfully");

	}

	public List<User> findAllUsers() {
		log.info("User Service:: findAllUsers");
		List<User> userList= userRepository.findAll();
		List<User> result=userList.stream().sorted(Comparator.comparing(User::getUsername)).toList();
		return result;
	}

	public User findUserById(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}

	public void updatePassword(Long userId,User inputUser) {
		User dbUser=findUserById(userId);
		log.info("User Service:: updateUser {} {}",dbUser.getId(),dbUser.getEmail());
		dbUser.setPassword(inputUser.getPassword());
		userRepository.save(dbUser);
		log.info("User updated successfully");

	}
	
	public void updateUser(Long userId,User inputUser) {
		User dbUser=findUserById(userId);
		log.info("User Service:: updateUser {} {}",dbUser.getId(),dbUser.getEmail());
		dbUser.setEmail(inputUser.getEmail());
		dbUser.setPassword(inputUser.getPassword());
		dbUser.setUsername(inputUser.getUsername());
		userRepository.save(dbUser);
		log.info("User updated successfully");

	}

	public void deleteUser(Long userId) {
		if(userRepository.existsById(userId)) {
			log.info("User Service:: deleteUser {}",userId);
			userRepository.deleteById(userId);
			log.info("User deleted successfully");
		}
		else {
			throw new ResourceNotFoundException("User not found");
		}

	}

	public User fetchUser(String username, String password) {
		log.info("User Service:: fetchUser {}",username);
		return userRepository.findByUsernameAndPassword(username,password);
	}

	public User findByUsername(String username) {
		log.info("User Service:: findByUsername {}",username);
		return userRepository.findByUsername(username);
	}
	
	public User findByEmail(String email) {
		log.info("User Service:: findByEmail {}",email);
		return userRepository.findByEmail(email);
	}
	
	public User findByUsernameOrEmail(String username, String email) {
		log.info("User Service:: findByUsernameOrEmail {} {}",username,email);
		return userRepository.findByUsernameOrEmail(username,email);
	}
}
