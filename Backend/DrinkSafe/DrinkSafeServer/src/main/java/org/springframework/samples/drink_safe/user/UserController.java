package org.springframework.samples.drink_safe.user;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
	public class UserController {
	
	//private final org.jboss.logging.Logger logger = LoggerFactory.logger(UserController.class);
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepository userRepo;
	
	
	@RequestMapping(method = RequestMethod.GET, path= "/users/new")
	public String saveUser(User user) {
		userRepo.save(user);
		return user.getUsername() + "has been added!";
	}
	
	@RequestMapping(method = RequestMethod.GET, path= "/users")
	public List<User> returnAllUsers(){
		logger.info("Entered into Controller Layer");
		List<User> x = userRepo.findAll();
		logger.info("");
		return x; 
	}
	
	@RequestMapping(method = RequestMethod.GET, path="users/find")
	public Optional<User> findUserbyID(@PathVariable("id") long id) {
		logger.info("Entered into Controller Layer");
        Optional<User> results = userRepo.findById(id);
        return results;
	}
	
		
	
	}


