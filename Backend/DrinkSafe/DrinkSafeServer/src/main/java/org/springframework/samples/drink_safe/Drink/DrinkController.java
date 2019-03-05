package org.springframework.samples.drink_safe.Drink;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.drink_safe.user.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
	public class DrinkController {
	private final Logger logger = LoggerFactory.getLogger(DrinkController.class);
	
	@Autowired
	DrinkRepository drinkRepo;
	
	
	
	@RequestMapping(method = RequestMethod.GET, path= "/drink/{DrinkId}/{alcPercent}/{volume}/")
	public String addDrink(@PathVariable("DrinkId") String DrinkId,@PathVariable("alcPercent") int alcPercent,@PathVariable("volume") int volume)
	{
		Drink drink = new Drink(DrinkId, alcPercent, volume);
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


