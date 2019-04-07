package org.springframework.samples.drink_safe.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.drink_safe.buddies.buddiesRepository;
import org.springframework.samples.drink_safe.friend.friendRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javassist.bytecode.Descriptor.Iterator;

@RestController
	public class UserController {
	
	//private final org.jboss.logging.Logger logger = LoggerFactory.logger(UserController.class);
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	friendRepository friendRepo;
	
	@Autowired
	buddiesRepository groupdRepo;
	
	
	

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
        logger.info("Number of users:"  + results.size());
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
		logger.info(u.getUsername()+ " has added " + u2.getUsername() + " as a friend");
	}
	

	@RequestMapping(method = RequestMethod.GET, path="/users/friend/addGroup/{user1Id}/{user2Id}")
	public void addGroup(@PathVariable("user1Id") String user1,@PathVariable("user2Id") String user2) {
		User u = userRepo.findByUsername(user1);
		User u2 =userRepo.findByUsername(user2);
		u2.toModifyBuddies().add(u);
		userRepo.save(u2);
		logger.info(u.getUsername()+ " has added " + u2.getUsername() + " into their group");
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/users/friend/leave/{userId}")
	public void leaveGroup(@PathVariable("userId") String user) {
		User u = userRepo.findByUsername(user);
		if(!u.toModifyBuddies().isEmpty()) {
			u.setInviter( new HashSet<User>());
		}
		else {
			java.util.Iterator<User> iter = u.toModifyInvitee().iterator();
			while(iter.hasNext())
			{
				User u2 = iter.next();
				u2.setInviter( new HashSet<User>());
			}
		}
		userRepo.save(u);
		logger.info(u.getUsername()+ " has removed themselves from the group");
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/users/friend/getGroup/{userId}")
	public List<User> getGroup(@PathVariable("userId") String user) {
		User u = userRepo.findByUsername(user);
		User u2;
		if (!u.toModifyBuddies().isEmpty())
		{
			
			java.util.Iterator<User> iter = u.toModifyBuddies().iterator();
			u2 = iter.next();
			java.util.Iterator<User> iter2 = u2.toModifyInvitee().iterator();
			List<User> returner = new ArrayList<User>();
			returner.add(u2);
			while(iter2.hasNext())
				returner.add(iter2.next());
			return returner;
		}
		else
		{
			java.util.Iterator<User> iter2 = u.toModifyInvitee().iterator();
			List<User> returner = new ArrayList<User>();
			returner.add(u);
			while(iter2.hasNext())
				returner.add(iter2.next());
			return returner;
		}
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, path="/users/edit/weight/{userId}/{weight}")
	public void editWeight(@PathVariable("userId") String user,@PathVariable("weight") int newWeight)
	{
		User u = userRepo.findByUsername(user);
		u.setWeight(newWeight);
		userRepo.save(u);
		logger.info(u.getUsername()+ " has changed weight to " + newWeight);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/users/edit/height/{userId}/{height}")
	public void editHeight(@PathVariable("userId") String user,@PathVariable("height") int newHeight)
	{
		User u = userRepo.findByUsername(user);
		u.setHeight(newHeight);
		userRepo.save(u);
		logger.info(u.getUsername()+ " has changed weight to " + newHeight);
	}
	


	
}


