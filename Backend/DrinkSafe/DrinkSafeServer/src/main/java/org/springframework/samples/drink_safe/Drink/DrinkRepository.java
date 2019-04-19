package org.springframework.samples.drink_safe.Drink;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.drink_safe.user.User;
import org.springframework.stereotype.Repository;

/**
 * The Drink Repository class
 * 
 * @author Jeremy and Nick
 *
 */
@Repository
public interface DrinkRepository extends CrudRepository<Drink, Long> {

	Drink findByDrinkid(String drinkId);

	List<Drink> findAllByFkuser(User u);

	Drink findByDid(int i);
	
	  @Modifying
	  @Transactional
	  @Query("delete from Drink d where d.fkuser = ?1")
	  void deleteInactiveUsers(String username);


}
