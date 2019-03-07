package org.springframework.samples.drink_safe.time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.samples.drink_safe.Drink.DrinkController;
import org.springframework.samples.drink_safe.user.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class timeController {
	private final Logger logger = LoggerFactory.getLogger(timeController.class);
	
	@RequestMapping(method = RequestMethod.GET, path="/users/friend/addFriends/{user1Id}/{user2Id}")
	public int updateTime(@PathVariable("userId") User user1, @PathVariable("time") int time) {
		
		
		
		User u = userRepo.findByUsername(user1);
		User u2 =userRepo.findByUsername(user2);
		u.getFriends().add(u2);
		userRepo.save(u);
		return user1 + "is now friends with " + user2;
	}

}
