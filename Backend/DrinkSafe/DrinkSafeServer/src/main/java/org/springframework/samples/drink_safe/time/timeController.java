package org.springframework.samples.drink_safe.time;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class timeController {
	private final Logger logger = LoggerFactory.getLogger(timeController.class);
	
	@Autowired
	timeRepository timeRepo;
	
	@RequestMapping(method = RequestMethod.GET, path ="/time")
	public List<time> listAllTime(){
		logger.info("Entered into control layer (For time)");
		List<time> r = (List<time>) timeRepo.findAll();
		return r;
	}
	


}
