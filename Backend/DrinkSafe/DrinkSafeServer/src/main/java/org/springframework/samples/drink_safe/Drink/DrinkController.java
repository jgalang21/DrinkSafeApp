package org.springframework.samples.drink_safe.Drink;

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
	public String addDrink(@PathVariable("DrinkId") String DrinkId,@PathVariable("alcPercent") int alcPercent,@PathVariable("volume") int volume, @PathVariable("fkuser") String fkuser)
	{
		long x = System.nanoTime();
		User u = userRepo.findByUsername(fkuser);
		time t = new time(u.getUsername().hashCode(),x,x+1000);
		u.setUser_time(t);
		userRepo.save(u);
		Drink drink = new Drink(DrinkId, alcPercent, volume,u);
		drinkRepo.save(drink);
		return "added";
	}
	

	
	
	@RequestMapping(method = RequestMethod.GET, path ="/drink")
	public String listAllDrinks(){
		String temp = "";
		logger.info("Entered into control layer (For drinks)");
		Iterable<Drink> r = drinkRepo.findAll();
		for(Drink a : r)
			temp += a + "\n";
		return temp;
	}

	
	}


// PRIMARY KEY (`drinkId`),
//CONSTRAINT `cusername` FOREIGN KEY (`cusername`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE);


