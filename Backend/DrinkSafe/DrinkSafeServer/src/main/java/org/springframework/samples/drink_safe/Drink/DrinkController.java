package org.springframework.samples.drink_safe.Drink;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
	public class DrinkController {
	private final Logger logger = LoggerFactory.getLogger(DrinkController.class);
	
	@Autowired
	DrinkRepository drinkRepo;
	
	
	
	@RequestMapping(method = RequestMethod.GET, path= "/drink/{DrinkId}/{alcPercent}/{volume}")
	public String addDrink(@PathVariable("DrinkId") String DrinkId,@PathVariable("alcPercent") int alcPercent,@PathVariable("volume") int volume)
	{
		Drink drink = new Drink(DrinkId, alcPercent, volume);
		drinkRepo.save(drink);
		return drink.getDrinkId() + " has been added. It contains " + alcPercent + " alcohol.";
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path ="/drink/")
	public String listAllDrinks(){
		String temp = "";
		logger.info("Entered into control layer (For drinks)");
		Iterable<Drink> r = drinkRepo.findAll();
		for(Drink a : r)
			temp += a + "\n";
		return temp;
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/drink/find/{DrinkId}")
	public Drink findByDrinkID(@PathVariable("DrinkId") String DrinkId) {
		logger.info("Finding Drink: " + DrinkId);
		Drink result = drinkRepo.findByDrinkId(DrinkId);
		return result;
		
	}
	
	
	
	}


