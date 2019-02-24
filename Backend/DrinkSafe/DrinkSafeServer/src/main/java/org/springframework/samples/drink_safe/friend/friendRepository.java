package org.springframework.samples.drink_safe.friend;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface friendRepository extends CrudRepository<friend, Long> {



}