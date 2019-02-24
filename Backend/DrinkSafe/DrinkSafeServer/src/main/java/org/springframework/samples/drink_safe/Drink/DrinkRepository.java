package org.springframework.samples.drink_safe.Drink;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.drink_safe.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends CrudRepository<User, Long> {

	void save(Drink drink);


	Drink findByDrinkId(String drinkId);


	Iterable<Drink> findAllDrinks();

}
