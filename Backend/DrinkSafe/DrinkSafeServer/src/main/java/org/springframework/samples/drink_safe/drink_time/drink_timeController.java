package org.springframework.samples.drink_safe.drink_time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
	public class drink_timeController {
	private final Logger logger = LoggerFactory.getLogger(drink_timeController.class);
	
	@Autowired
	drink_timeRepository dtRepo;
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/link/{dtusername}/{tid}")
	public drink_time addLink(@PathVariable("dtusername") String dtusername, @PathVariable("tid") int tid){
		
		
		logger.info("Linking " + dtusername + "with " + tid + " left.");
		
		drink_time newUser = new drink_time(dtusername, tid);
		dtRepo.save(newUser);
	
		return newUser; 
		
	}
		
	
	
}
	
	

	
	
	
	


