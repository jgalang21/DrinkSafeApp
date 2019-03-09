package org.springframework.samples.drink_safe.time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.drink_safe.Drink.Drink;
import org.springframework.samples.drink_safe.Drink.DrinkController;
import org.springframework.samples.drink_safe.Drink.DrinkRepository;
import org.springframework.samples.drink_safe.user.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class timeController {
	private final Logger logger = LoggerFactory.getLogger(timeController.class);
	
	@Autowired
	timeRepository timeRepo;
	
	@RequestMapping(method = RequestMethod.GET, path ="/time")
	public String listAllTime(){
		String temp = "";
		logger.info("Entered into control layer (For time)");
		Iterable<time> r = timeRepo.findAll();
		for(time a : r)
			temp += a + "\n";
		return temp;
	}
	


}
