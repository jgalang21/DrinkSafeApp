package org.springframework.samples.drink_safe.time;

import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.drink_safe.Drink.Drink;
import org.springframework.samples.drink_safe.Drink.DrinkRepository;
import org.springframework.samples.drink_safe.drink_time.drink_time;
import org.springframework.samples.drink_safe.drink_time.drink_timeRepository;
import org.springframework.samples.drink_safe.user.User;
import org.springframework.samples.drink_safe.user.UserRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Time controller class
 * 
 * @author Jeremy and Nick
 *
 */
@RestController
public class timeController {
	private final Logger logger = LoggerFactory.getLogger(timeController.class);
	
	@Autowired
	timeRepository timeRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	DrinkRepository drinkRepo;
	
	@Autowired
	drink_timeRepository dtRepo;
	
	/**
	 * 
	 * @return - all the user's times
	 */
	@RequestMapping(method = RequestMethod.GET, path ="/time")
	public List<time> listAllTime(){
		logger.info("listing user times");
		List<time> r = (List<time>) timeRepo.findAll();
		return r;
	}
	
	/**
	 * Clears the user's time
	 * @param user - the user we want to clear
	 */
	@RequestMapping(method = RequestMethod.GET, path ="/time/clear/{userId}")
	public void clearTime(@PathVariable("userId") String user){
		User u = userRepo.findByUsername(user);
		time t = timeRepo.findByTid(u.getUser_time().getTid());
		logger.info("Tid: " + t.getTid());
		drink_time dt = dtRepo.findByPerson(user);
		dtRepo.delete(dt);
		timeRepo.delete(t);
		List<Drink> results = drinkRepo.findAllByFkuser(u);
		drinkRepo.deleteAll(results);
		u.setBAC(0);
		u.setGuestStatus(0);
		timeRepo.save(t);
		//dtRepo.save(dt);
		//drinkRepo.saveAll(results);
		String drink_list="";
		for(Drink d: results)
			drink_list +=d.getDid()+ " "+ d.getDrinkid();
		logger.info("dt: " + dt.getPerson() + " time: " + t.getTid() + " drink list: " + drink_list);
	}
	


}
