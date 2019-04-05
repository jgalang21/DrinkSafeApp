package org.springframework.samples.drink_safe.user;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {




	List<User> findAllByGuestStatus(int guest_status);

	User findByUsername(String id);


	//Optional<User> getFriends(String id);




}