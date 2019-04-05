package org.springframework.samples.drink_safe.buddies;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface buddiesRepository extends CrudRepository<buddies, Long> {



}