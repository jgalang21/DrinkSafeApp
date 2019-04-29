package org.springframework.samples.drink_safe.Drink;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.drink_safe.time.time;
import org.springframework.samples.drink_safe.time.timeRepository;
import org.springframework.samples.drink_safe.user.User;
import org.springframework.samples.drink_safe.user.UserRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class does the work with all the 
 * @author Jeremy and Nick
 *
 */
@RestController
public class DrinkController {
	private final Logger logger = LoggerFactory.getLogger(DrinkController.class);

	@Autowired
	DrinkRepository drinkRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	timeRepository timeRepo;

	/**
	 * Adds a drink to the database
	 * 
	 * @param DrinkId - the name of the drink
	 * @param alcPercent - the drink's percent of alcohol
	 * @param volume - the total amount in ml
	 * @param fkuser - the user who had the drink
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/drink/new/{DrinkId}/{alcPercent}/{volume}/{fkuser}")
	public void addDrink(@PathVariable("DrinkId") String DrinkId, @PathVariable("alcPercent") int alcPercent,
			@PathVariable("volume") int volume, @PathVariable("fkuser") String fkuser) {
		long x = System.nanoTime();
		User u = userRepo.findByUsername(fkuser);
		List<Drink> r = (List<Drink>) drinkRepo.findAll();
		List<Integer> dids = new ArrayList<Integer>();
		int did=0;
		boolean full_size=true;
		for(int i =0; i< r.size();i++)
		{
			dids.add(r.get(i).getDid());
		}
		for(int i=0;i<r.size();i++)
		{
			if(!(dids.contains(i)))
			{
				did=i;
				i=r.size();
				full_size=false;
			}
		}
		if(full_size)
			did=r.size();
		Drink drink = new Drink(did, DrinkId, alcPercent, volume, u);
		drinkRepo.save(drink);
		if (u.getUser_time() == null) {
			List<time> s = (List<time>) timeRepo.findAll();
			int tid=s.size();
			double cBAC = calculateBAC(u, drink);
			time t;
			if (cBAC < 0.08) {
				t = new time(tid, System.currentTimeMillis(), System.currentTimeMillis());
			} else {
				long time_to_add = (long) ((cBAC - 0.08) / 0.015);
				t = new time(tid, System.currentTimeMillis(), System.currentTimeMillis() + (time_to_add * 3600000));
			}
			u.setBAC(cBAC);
			u.setUser_time(t);
			userRepo.save(u);
		} else {
			double cBAC = u.getBAC() + calculateBAC(u, drink);
			cBAC -= (0.015 * ((System.currentTimeMillis() - u.getUser_time().toModifyTime_start()) / 3600000));
			if (cBAC < 0.08) {
				u.getUser_time().setTime_start(System.currentTimeMillis());
				u.getUser_time().setTime_finish(System.currentTimeMillis());
			} else {
				u.getUser_time().setTime_start(System.currentTimeMillis());
				long time_to_add = (long) (3600000 * ((cBAC - 0.08) / 0.015));
				logger.info("Adding " + time_to_add * 3600000 + " hours to time finish");
				u.getUser_time().setTime_finish(u.getUser_time().toModifyTime_finish() + time_to_add);
			}
			u.setBAC(cBAC);
			userRepo.save(u);
		}
		logger.info(fkuser + " had added " + DrinkId + " as a drink");
	}

	/**
	 * Displays all the drinks currently in the database
	 * @return r - a list of all the drinks stored
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/drink")
	public List<Drink> listAllDrinks() {
		logger.info("Entered into control layer (For drinks)");
		List<Drink> r = (List<Drink>) drinkRepo.findAll();
		logger.info("Displaying all drinks in database");
		return r;
	}

	/**
	 * @param DrinkId - the name of the drink
	 * @return result - the actual drink object (which contains its contents)
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/drink/find/{DrinkId}")
	public Drink findDrinkbyID(@PathVariable("DrinkId") String DrinkId) {
		logger.info("Finding drink: " + DrinkId);
		Drink result = drinkRepo.findByDrinkid(DrinkId);
		return result;
	}

	/**
	 * @param u - the username we're checking
	 * @return whether the user is sober or not
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/drink/check/{User}")
	public boolean isSober(@PathVariable("User") String u) {
		User username = userRepo.findByUsername(u);
		logger.info("Checking " + u + "'s status..");

		if (username.getUser_time() != null) {
			if (username.getUser_time().toModifyTime_finish() <= System.currentTimeMillis()) {
				logger.info(u + " is sober");
				return true;
			}

			else {
				logger.info(u + " is not sober");
				return false;
			}
		} else
			logger.info(u + " is not sober");
		return true;

	}

	/**
	 * Calculates the user's blood alcohol level (BAC)
	 * 
	 * @param username - the user we're looking at
	 * @param drink - the drink the user had
	 * @return - the user's BAC
	 */
	public double calculateBAC(User username, Drink drink) {
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

		double result = ((((drink.getAlcpercent() / 100.0) * drink.getVolume()) * 5.14) / (weight * ratio));
		return result;
	}

}
