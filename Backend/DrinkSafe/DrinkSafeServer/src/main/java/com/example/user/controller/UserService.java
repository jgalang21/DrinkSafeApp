package com.example.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public User create(User newUser) {
		// validate model
		// authenticate user, etc
		User.create(newUser);
		return newUser;
	}

	public List<User> getAll() {
        //logger.info("Entered into Controller Layer");
        List<User> results = UserRepository.findAll();
        //logger.info("Number of Records Fetched:" + results.size());
        return results;
	}

	public User get(long id) {
		// TODO Auto-generated method stub
		return null;
	}
}