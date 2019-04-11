package org.springframework.samples.drink_safe.Drink;

import java.util.HashMap;
import java.util.Iterator;
import java.sql.Time;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.drink_safe.drink_time.drink_time;
import org.springframework.samples.drink_safe.time.time;
import org.springframework.samples.drink_safe.time.timeRepository;
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
	
	@Autowired
	timeRepository timeRepo;

	@RequestMapping(method = RequestMethod.GET, path = "/drink/new/{DrinkId}/{alcPercent}/{volume}/{fkuser}")
	public void addDrink(@PathVariable("DrinkId") String DrinkId, @PathVariable("alcPercent") int alcPercent,
			@PathVariable("volume") int volume, @PathVariable("fkuser") String fkuser) {
		long x = System.nanoTime();
		User u = userRepo.findByUsername(fkuser);
		List<Drink> r = (List<Drink>) drinkRepo.findAll();
		int did;
		if (r.isEmpty())
			did = 0;
		else
			did = r.size();
		Drink drink = new Drink(did, DrinkId, alcPercent, volume, u);
		drinkRepo.save(drink);
		if(u.getUser_time()==null)
		{
		int tid;
		List<time> s = (List<time>) timeRepo.findAll();
		if(s.isEmpty())
			tid=0;
		else
			tid = s.size();
		time t = new time(tid,System.nanoTime(),System.nanoTime()+calculateBAC(u));
		u.setUser_time(t);
		userRepo.save(u);
		}
		else
		{
			u.getUser_time().setTime_finish(u.getUser_time().getTime_finish()+calculateBAC(u));
			userRepo.save(u);
		}
		logger.info(fkuser + " had added " + DrinkId + " as a drink");
	}

	@RequestMapping(method = RequestMethod.GET, path = "/drink")
	public List<Drink> listAllDrinks() {
		logger.info("Entered into control layer (For drinks)");
		List<Drink> r = (List<Drink>) drinkRepo.findAll();
		logger.info("displaying all drinks");
		return r;

	}

	public long calculateBAC(@PathVariable("username") User username) {
		double height = username.getHeight();
		double weight = username.getWeight();
		int gender = username.getGender();
		double ratio = 0.0;

		if (gender == 1) { // constant ratio for women
			ratio = 0.66;
		} else {
			ratio = 0.73;
		} // constant ratio for men

		double temp = 0.0;

		String drinkList = username.getDrinks(); // all of the user's drinks
		String[] drinkArr; 
		
		drinkArr = drinkList.split(" ");
		
		
		for(int i = 0; i < drinkArr.length; i++) {
			
		}
		
		
		return 0;
	}

}
