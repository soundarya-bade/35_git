package com.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.restapi.entities.User;
import com.restapi.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
		
	@Autowired
	UserService userService;
	
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public void createUser(@RequestBody User user) {
		log.info("User Controller:: createUser {}",user.getEmail());
		userService.createUser(user);
		
	}
	
	@GetMapping("{userId}")
	public User getUserById(@PathVariable Long userId) {
		log.info("User Controller:: getUserById {}",userId);
		return userService.findUserById(userId);
		
	}
	
	@PutMapping("{userId}")
	public void updateUser(@PathVariable Long userId,@RequestBody User user) {
		log.info("User Controller:: updateUser {}",user.getEmail());
		userService.updateUser(userId, user);
		
	}
	
	@PatchMapping("changePwd/{userId}")
	public void updatePassword(@PathVariable Long userId,@RequestBody User user) {
		log.info("User Controller:: updateUser {}",user.getEmail());
		userService.updatePassword(userId, user);
		
	}

	@DeleteMapping("{userId}")
	public void deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
		
	}
	
	@GetMapping("login/{username}/{password}")
	public User fetchUser(@PathVariable String username,@PathVariable String password) {
		log.info("User Controller:: fetchUser {}",username);
		return userService.fetchUser(username,password);
		
	}
	
	@GetMapping("findByUsername/{username}")
	public User findByUsername(@PathVariable String username) {
		log.info("User Controller:: findByUsername {}",username);
		return userService.findByUsername(username);
		
	}
	
	@GetMapping("findByEmail/{email}")
	public User findByEmail(@PathVariable String email) {
		log.info("User Controller:: findByEmail {}",email);
		return userService.findByEmail(email);
		
	}
	
	@GetMapping("findByUsernameOrEmail/{username}/{email}")
	public User findByUsernameOrEmail(@PathVariable String username,@PathVariable String email) {
		log.info("User Controller:: findByUsernameOrEmail {} {}",username,email);
		return userService.findByUsernameOrEmail(username,email);
		
	}
}
