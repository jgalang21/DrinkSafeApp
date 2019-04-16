package org.springframework.samples.drink_safe.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * The friend service class
 * 
 * @author Jeremy and Nick
 *
 */
@Service
public class friendService {
	@Autowired
	friendRepository friendRepo;

	

	public Iterable<friend> getAll() {
      
        Iterable<friend> results = friendRepo.findAll();
       
        return results;
	}

	public friend get(long id) {
		friendRepo.findById(id);
		 
		return null;
	}
	
}