package com.example.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
	public class UserController {
		@Autowired // @Autowired attribute injects UserService into this class
		UserService userService;
		
		@GetMapping("/users")
		List<User> get() {
		    return userService.getAll();
		}
		
		@GetMapping("/users/{id}")
		User get(@PathVariable long id) {
		    return userService.get(id);
		}

		@PostMapping("/users")
		User post(@RequestBody User user) {
		    return userService.create(user);
		}
	}
