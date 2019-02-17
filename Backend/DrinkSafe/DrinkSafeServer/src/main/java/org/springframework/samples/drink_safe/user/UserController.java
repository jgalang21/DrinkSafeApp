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
	
	
	@RequestMapping(method = RequestMethod.GET, path= "/users/new/{username}/{password}/{height}/{weight}/{gender}/{gs}")
	public String saveUser(@PathVariable("username") String username,@PathVariable("password") String password,@PathVariable("height") int height,@PathVariable("weight") int weight,@PathVariable("gender") int gender, @PathVariable("gs") int guest_status) {
		User user = new User(username,password,height,weight,gender,guest_status);
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
	
	@RequestMapping(method = RequestMethod.GET, path="/users/find/un/{userId}")
	public Optional<User> findUserbyID(@PathVariable("userId") String id) {
		logger.info("Finding user: "+id);
        Optional<User> results = userRepo.findByUsername(id);
        return results;
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/users/find/gs/{guest_status}")
	public List<User> findByGuest_Status(@PathVariable("guest_status") int guest_status) {
		logger.info("Finding user: "+guest_status);
        List<User> results = userRepo.findAllByGuestStatus(guest_status);
        return results;
	}
	
		
	
	}


