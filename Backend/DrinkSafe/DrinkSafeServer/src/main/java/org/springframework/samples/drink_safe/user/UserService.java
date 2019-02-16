package org.springframework.samples.drink_safe.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public User create(User newUser) {
		// validate model
		// authenticate user, etc
		User.create(newUser);
		return newUser;
	}

	public List<User> getAll() {
      
        List<User> results = userRepository.findAll();
       
        return results;
	}

	public User get(long id) {
		userRepository.findById(id);
		 
		return null;
	}
	
}