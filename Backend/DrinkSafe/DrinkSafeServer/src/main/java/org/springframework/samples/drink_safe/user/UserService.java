package org.springframework.samples.drink_safe.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * The User service class
 * 
 * @author Jeremy and Nick
 *
 */
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public User create(User newUser) {

		User.create(newUser);
		return newUser;
	}

	public Iterable<User> getAll() {

		Iterable<User> results = userRepository.findAll();

		return results;
	}

	public User get(String id) {
		User u = userRepository.findByUsername(id);

		return u;
	}

}