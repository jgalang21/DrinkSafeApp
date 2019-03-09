package org.springframework.samples.drink_safe.user;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String u2);


	List<User> findAllByGuestStatus(int guest_status);


	//Optional<User> getFriends(String id);




}