package org.springframework.samples.drink_safe.user;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	

	
	
	

	@RequestMapping(method = RequestMethod.GET, path= "/users/new/{username}/{name}/{password}/{height}/{weight}/{gender}/{guestStatus}")
	public void saveUser(@PathVariable("username") String username,@PathVariable("name") String name,@PathVariable("password") String password,@PathVariable("height") int height,@PathVariable("weight") int weight,@PathVariable("gender") int gender, @PathVariable("guestStatus") int guestStatus)
	{
		User user = new User(username,name,password,height,weight,gender,guestStatus);
		userRepo.save(user);
		logger.info("saving new user: " + user.getUsername());
	}
	
	@RequestMapping(method = RequestMethod.GET, path= "/users")
	public List<User> returnAllUsers(){
		logger.info("Displaying all users");
        List<User> results = (List<User>) userRepo.findAll();
        logger.info("Number of usrs:" + results.size());
        return results;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/users/find/id/{userId}")
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
	

	
	@RequestMapping(method = RequestMethod.GET, path="/users/friend/addFriends/{user1Id}/{user2Id}")
	public void addFriends(@PathVariable("user1Id") String user1,@PathVariable("user2Id") String user2) {
		User u = userRepo.findByUsername(user1);
		User u2 =userRepo.findByUsername(user2);
		u.toModifyFriends().add(u2);
		userRepo.save(u);
		logger.info(u.getUsername()+ "has added" + u2.getUsername() + "as a friend");
	}
	
/*
	@RequestMapping(method = RequestMethod.GET, path="/users/new/{username}/{drinkid}/{amount}")
	public void giveDrink(@PathVariable("username") User username, @PathVariable("drinkid") Drink drinkid) {
		
		logger.info(username.getUsername() + " drinks a " + drinkid.getDrinkid() + " with " +  drinkid.getAlcpercent() + "alcohol");
		User found = userRepo.findByUsername(username.getUsername()); // find the user	
		found.giveDrink(drinkid);
		logger.info("Drink successfully added!");
		
	}
*/	
	
}


