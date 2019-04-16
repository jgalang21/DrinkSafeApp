package org.springframework.samples.drink_safe.time;

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


}
