package org.springframework.samples.drink_safe.buddies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class buddiesService {
	@Autowired
	buddiesRepository groupRepo;

	public buddies create(buddies new_rel) {
		// validate model
		// authenticate user, etc
		buddies.create(new_rel);
		return new_rel;
	}

	public Iterable<buddies> getAll() {
      
        Iterable<buddies> results = groupRepo.findAll();
       
        return results;
	}

	public buddies get(long id) {
		groupRepo.findById(id);
		 
		return null;
	}
	
	
}