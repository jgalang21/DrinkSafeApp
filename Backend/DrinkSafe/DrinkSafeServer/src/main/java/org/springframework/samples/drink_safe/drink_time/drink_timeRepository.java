package org.springframework.samples.drink_safe.drink_time;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The Drink Repository
 * 
 * @author Jeremy and Nick
 *
 */
@Repository
public interface drink_timeRepository extends CrudRepository<drink_time, Long> {

	drink_time findByPerson(String user);
	
	  @Modifying
	  @Transactional
	  @Query("delete from drink_time dt where dt.person = ?1")
	  void deleteByUsername(String username);



}