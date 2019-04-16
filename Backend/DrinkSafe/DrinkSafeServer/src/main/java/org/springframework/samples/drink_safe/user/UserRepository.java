package org.springframework.samples.drink_safe.user;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The UserRepository class
 * 
 * @author Jeremy and Nick
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findAllByGuestStatus(int guest_status);

	User findByUsername(String id);

}