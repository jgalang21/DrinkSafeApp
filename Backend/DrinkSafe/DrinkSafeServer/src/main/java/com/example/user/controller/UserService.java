package com.example.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public void create(User newUser) {
		// validate model
		// authenticate user, etc
		UserRepository.create(newUser);
	}

	public User get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}