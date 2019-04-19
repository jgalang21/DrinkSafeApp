package org.springframework.samples.drink_safe.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.drink_safe.Drink.Drink;
import org.springframework.samples.drink_safe.friend.friendRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController class
 * 
 * This class interacts with the user's credentials in the database
 * 
 * @author Jeremy and Nick
 *
 */
@RestController
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRepository userRepo;

	@Autowired
	friendRepository friendRepo;

	public UserController() {
	}

	/**
	 * Save the user into the database
	 * 
	 * @param username    - the user's name as an email
	 * @param name        - the actual name of the user
	 * @param password    - the user's password
	 * @param height      - the height of the user
	 * @param weight      - the weight of the user
	 * @param gender      - the user's gender, 0 = male, 1 = female
	 * @param guestStatus - whether the user is drunk or not
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/users/new/{username}/{name}/{password}/{height}/{weight}/{gender}/{guestStatus}")
	public void saveUser(@PathVariable("username") String username, @PathVariable("name") String name,
			@PathVariable("password") String password, @PathVariable("height") int height,
			@PathVariable("weight") int weight, @PathVariable("gender") int gender,
			@PathVariable("guestStatus") int guestStatus) {
		User user = new User(username, name, password, height, weight, gender, guestStatus);
		userRepo.save(user);
		logger.info("saving new user: " + user.getUsername());
	}

	/**
	 * Returns a list of all the users in the database
	 * 
	 * @return results - a list of all the usernames in the database
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/users")
	public List<User> returnAllUsers() {
		logger.info("Displaying all users");
		List<User> results = (List<User>) userRepo.findAll();
		logger.info("Number of users:" + results.size());
		return results;

	}

	/**
	 * Finds a user in the database
	 * 
	 * @param id - the username
	 * @return the user as an object
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/users/find/id/{userId}")
	public User findUserbyID(@PathVariable("userId") String id) {
		logger.info("Finding user: " + id);
		User results = userRepo.findByUsername(id);
		return results;
	}

	/**
	 * Find a list of all the sober and drunk people
	 * 
	 * @param guest_status - whether we're looking for sober/drunk people
	 * @return results - a list of all guests who are sober or drunk
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/users/find/gs/{guest_status}")
	public List<User> findByGuest_Status(@PathVariable("guest_status") int guest_status) {
		logger.info("Finding user: " + guest_status);
		List<User> results = userRepo.findAllByGuestStatus(guest_status);
		return results;
	}

	/**
	 * Adding friend relation between 2 users
	 * 
	 * @param user1 - the first user
	 * @param user2 - the second user
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/users/friend/addFriends/{user1Id}/{user2Id}")
	public void addFriends(@PathVariable("user1Id") String user1, @PathVariable("user2Id") String user2) {
		User u = userRepo.findByUsername(user1);
		User u2 = userRepo.findByUsername(user2);
		u.toModifyFriends().add(u2);
		userRepo.save(u);
		logger.info(u.getUsername() + " has added " + u2.getUsername() + " as a friend");
	}

	/**
	 * Edits the user's weight
	 * 
	 * @param user      - the user
	 * @param newWeight - the modified weight
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/users/edit/weight/{userId}/{weight}")
	public void editWeight(@PathVariable("userId") String user, @PathVariable("weight") int newWeight) {
		User u = userRepo.findByUsername(user);
		u.setWeight(newWeight);
		userRepo.save(u);
		logger.info(u.getUsername() + " has changed weight to " + newWeight);
	}

	/**
	 * Edits the user's height
	 * 
	 * @param user      - the user
	 * @param newHeight - the user's new height
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/users/edit/height/{userId}/{height}")
	public void editHeight(@PathVariable("userId") String user, @PathVariable("height") int newHeight) {
		User u = userRepo.findByUsername(user);
		u.setHeight(newHeight);
		userRepo.save(u);
		logger.info(u.getUsername() + " has changed height to " + newHeight);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/users/edit/password/{userId}/{password}")
	public void editPassword(@PathVariable("userId") String user, @PathVariable("password") String password) {
		User u = userRepo.findByUsername(user);
		u.setPassword(password);
		userRepo.save(u);
		logger.info(u.getUsername() + " has changed password to " + password);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/users/edit/name/{userId}/{name}")
	public void editName(@PathVariable("userId") String user, @PathVariable("name") String name) {
		User u = userRepo.findByUsername(user);
		u.setName(name);
		userRepo.save(u);
		logger.info(u.getUsername() + " has changed name to " + name);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/users/list/drink/{userId}")
	public List<Drink> listDrinks(@PathVariable("userId") String user) {
		User u = userRepo.findByUsername(user);
		Set<Drink> list_of_drink =  u.toModifyDrinks();
		logger.info(u.getUsername() + " is listing all their drinks");
		List<Drink> returner = new ArrayList<Drink>(); 
	    for (Drink x : list_of_drink) 
	      returner.add(x);
		return returner;
	}
	
	

}
