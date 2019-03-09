package org.springframework.samples.drink_safe.time;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface timeRepository extends CrudRepository<time, Long> {


}
