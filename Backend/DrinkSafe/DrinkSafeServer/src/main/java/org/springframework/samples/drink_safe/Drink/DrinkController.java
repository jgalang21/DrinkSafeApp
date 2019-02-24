package org.springframework.samples.drink_safe.drink;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.drink_safe.user.UserController;
import org.springframework.samples.drink_safe.user.UserRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
	public class DrinkController {
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepository drinkRepo;
	
	
	
	
	
	
	}


