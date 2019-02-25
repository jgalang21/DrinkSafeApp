package org.springframework.samples.drink_safe.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class friendService {
	@Autowired
	friendRepository friendRepo;

	public friend create(friend new_rel) {
		// validate model
		// authenticate user, etc
		friend.create(new_rel);
		return new_rel;
	}

	public Iterable<friend> getAll() {
      
        Iterable<friend> results = friendRepo.findAll();
       
        return results;
	}

	public friend get(long id) {
		friendRepo.findById(id);
		 
		return null;
	}
	
}