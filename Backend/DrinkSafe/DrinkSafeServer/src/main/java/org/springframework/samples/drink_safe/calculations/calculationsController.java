package org.springframework.samples.drink_safe.calculations;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.samples.drink_safe.Drink.Drink;
import org.springframework.samples.drink_safe.user.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class calculationsController {

	private final Logger logger = LoggerFactory.getLogger(calculationsController.class);

	@RequestMapping(method = RequestMethod.GET, path = "/calculate/{username}/")
	public double calculateBAC(@PathVariable("username") User username, @PathVariable("hours") double time) {
		double height = username.getHeight();
		double weight = username.getWeight();
		int gender = username.getGender();
		double ratio = 0.0; 

		if(gender == 1) { //constant ratio for women
			ratio = 0.66; 
		}
		else {ratio = 0.73; } //constant ratio for men
		
		
		double temp = 0.0; 
		
		Set<Drink> x = username.getDrinks(); //all of the user's drinks
		Iterator<Drink> r = x.iterator(); 
		HashMap<String, Integer> tempList = new HashMap<String, Integer>();
		int j =0; 
		
		while(r.hasNext()) {
			Drink p = r.next(); 
			
			if(!tempList.containsKey(p.getDrinkid())) {
				tempList.put(p.getDrinkid(), j++);
			}
			else {
				
			}
	
			
		}
		
		//might be wrong, we might need to get the number of x amount of drinks such as the example here:
		//https://www.teamdui.com/bac-widmarks-formula/
		//temp += p.getVolume() * p.getAlcpercent();  
		
		
		
		
		
		
		
		return 0;

	}
}

/**
 * @RequestMapping(method = RequestMethod.GET, path=
 *                        "/drink/new/{DrinkId}/{alcPercent}/{volume}/{fkuser}")
 *                        public String addDrink(@PathVariable("DrinkId") String
 *                        DrinkId,@PathVariable("alcPercent") int
 *                        alcPercent,@PathVariable("volume") int
 *                        volume, @PathVariable("fkuser") String fkuser) { User
 *                        u = userRepo.findByUsername(fkuser); Drink drink = new
 *                        Drink(DrinkId, alcPercent, volume,u);
 *                        drinkRepo.save(drink); return "added"; }
 **/