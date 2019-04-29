package org.springframework.samples.drink_safe.time;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * The time repository class
 * 
 * @author Jeremy and Nick
 *
 */
@Repository
public interface timeRepository extends CrudRepository<time, Long> {


	
	
	  @Modifying
	  @Transactional
	  @Query("delete from time t where t.tid = ?1")
	  void deleteByTid(int tid);

	time findByTid(int tid);


}
