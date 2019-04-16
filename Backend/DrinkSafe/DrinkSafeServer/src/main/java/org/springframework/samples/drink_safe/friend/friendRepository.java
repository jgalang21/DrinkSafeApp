package org.springframework.samples.drink_safe.friend;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The friend repository class
 * 
 * @author Jeremy and NIck
 *
 */
@Repository
public interface friendRepository extends CrudRepository<friend, Long> {



}