package org.springframework.samples.drink_safe.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.drink_safe.friend.friend;
import org.springframework.samples.drink_safe.friend.friendRepository;
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
	
	@Autowired
	friendRepository friendRepo;
	
	
	
	@RequestMapping(method = RequestMethod.GET, path= "/users/new/{username}/{password}/{height}/{weight}/{gender}/{guestStatus}")
	public String saveUser(@PathVariable("username") String username,@PathVariable("password") String password,@PathVariable("height") int height,@PathVariable("weight") int weight,@PathVariable("gender") int gender, @PathVariable("guestStatus") int guestStatus)
	{
		User user = new User(username,password,height,weight,gender,guestStatus);
		userRepo.save(user);
		return user.getUsername() + "has been added!";
	}
	
	@RequestMapping(method = RequestMethod.GET, path= "/users")
	public String returnAllUsers(){
		String returner="";
		logger.info("Entered into Controller Layer");
		Iterable<User> x = userRepo.findAll();
		for(User a:x)
			returner+=a +"\n";
		return returner;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/users/find/un/{userId}")
	public User findUserbyID(@PathVariable("userId") String id) {
		logger.info("Finding user: "+id);
        User results = userRepo.findByUsername(id);
        return results;
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/users/find/gs/{guest_status}")
	public List<User> findByGuest_Status(@PathVariable("guest_status") int guest_status) {
		logger.info("Finding user: "+guest_status);
        List<User> results = userRepo.findAllByGuestStatus(guest_status);
        return results;
	}
	
	/*
	@RequestMapping(method = RequestMethod.GET, path="/users/friend/getFriends/{userId}")
	public String getFriends(@PathVariable("userId") String id) {
		
	}	
	*/
	
	@RequestMapping(method = RequestMethod.GET, path="/users/friend/addFriends/{user1Id}/{user2Id}")
	public String addFriends(@PathVariable("user1Id") String user1,@PathVariable("user2Id") String user2) {
		friend new_rel= new friend(user1,user2);
		friendRepo.save(new_rel);
		return user1 + "is now friends with " + user2;
	}	
	
}


