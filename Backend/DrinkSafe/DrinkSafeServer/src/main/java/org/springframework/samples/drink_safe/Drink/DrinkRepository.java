package org.springframework.samples.drink_safe.Drink;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends CrudRepository<Drink, Long> {

	Drink findByDrinkid(String drinkId);




}
