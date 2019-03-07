package org.springframework.samples.drink_safe.user;

import java.time.LocalTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.drink_safe.Drink.Drink;
import org.springframework.samples.drink_safe.friend.friendRepository;
import org.springframework.samples.drink_safe.time.time;
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
	

	
	@RequestMapping(method = RequestMethod.GET, path="/users/friend/addFriends/{user1Id}/{user2Id}")
	public String addFriends(@PathVariable("user1Id") String user1,@PathVariable("user2Id") String user2) {
		User u = userRepo.findByUsername(user1);
		User u2 =userRepo.findByUsername(user2);
		u.getFriends().add(u2);
		userRepo.save(u);
		return user1 + "is now friends with " + user2;
	}
	

	@RequestMapping(method = RequestMethod.GET, path="/users/new/{username}/{drinkid}/{amount}")
	public void giveDrink(@PathVariable("username") User username, @PathVariable("drinkid") Drink drinkid) {
		
		logger.info(username.getUsername() + " drinks a " + drinkid.getDrinkid() + " with " +  drinkid.getAlcpercent() + "alcohol");
		User found = userRepo.findByUsername(username.getUsername()); // find the user
	
		
		found.giveDrink(drinkid);
		logger.info("Drink successfully added!");
		
	}
	@RequestMapping(method = RequestMethod.GET, path="/users/time/addTime/{user1Id}")
	public String addTime(@PathVariable("user1Id") String user1) {
		User u = userRepo.findByUsername(user1);
		time t = new time(u.getUsername().hashCode(),System.nanoTime(),System.nanoTime()+1000);
		u.setUser_time(t);
		userRepo.save(u);
		return user1 + "now has " + (t.getTime_finish() -t.getTime_start());
	}
	

	
	
}


