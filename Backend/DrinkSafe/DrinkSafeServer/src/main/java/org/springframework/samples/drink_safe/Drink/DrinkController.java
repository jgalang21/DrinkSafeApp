package org.springframework.samples.drink_safe.Drink;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.drink_safe.time.time;
import org.springframework.samples.drink_safe.user.User;
import org.springframework.samples.drink_safe.user.UserRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
	public class DrinkController {
	private final Logger logger = LoggerFactory.getLogger(DrinkController.class);
	
	@Autowired
	DrinkRepository drinkRepo;
	
	@Autowired
	UserRepository userRepo;
	
	
	
	@RequestMapping(method = RequestMethod.GET, path= "/drink/new/{DrinkId}/{alcPercent}/{volume}/{fkuser}")
	public void addDrink(@PathVariable("DrinkId") String DrinkId,@PathVariable("alcPercent") int alcPercent,@PathVariable("volume") int volume, @PathVariable("fkuser") String fkuser)
	{
		long x = System.nanoTime();
		User u = userRepo.findByUsername(fkuser);
		time t = new time(u.getUsername().hashCode(),x,x+1000);
		u.setUser_time(t);
		userRepo.save(u);
		Drink drink = new Drink(DrinkId, alcPercent, volume,u);
		drinkRepo.save(drink);
		logger.info(fkuser + "had added" +DrinkId + "as a drink");
	}
	

	
	
	@RequestMapping(method = RequestMethod.GET, path ="/drink")
	public List<Drink> listAllDrinks(){
		logger.info("Entered into control layer (For drinks)");
		List<Drink> r = (List<Drink>) drinkRepo.findAll();
		logger.info("displaying all drinks");
		return r;
		
	}

	
	}


// PRIMARY KEY (`drinkId`),
//CONSTRAINT `cusername` FOREIGN KEY (`cusername`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE);


