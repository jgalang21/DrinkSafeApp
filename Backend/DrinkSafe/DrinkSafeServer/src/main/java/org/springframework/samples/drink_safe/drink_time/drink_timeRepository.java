package org.springframework.samples.drink_safe.drink_time;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface drink_timeRepository extends CrudRepository<drink_time, Long> {

//	drink_time save(drink_time newUser);



}