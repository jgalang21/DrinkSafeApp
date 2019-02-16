package org.springframework.samples.drink_safe.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String id);


	List<User> findAllByGuestStatus(int guest_status);




}