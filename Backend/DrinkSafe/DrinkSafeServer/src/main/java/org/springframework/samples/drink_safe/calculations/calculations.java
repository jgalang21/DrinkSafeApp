/*
package org.springframework.samples.drink_safe.calculations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.samples.drink_safe.Drink.Drink;
import org.springframework.samples.drink_safe.user.User;

@Entity // the @Entity annotation tells the complier that this is a Database mapped
		// object
@Table(name = "calculations")
public class calculations {

	@Column(name = "user")
	@NotFound(action = NotFoundAction.IGNORE)
	private User username;

	@Column(name = "drink")
	@NotFound(action = NotFoundAction.IGNORE)
	private Drink drink;


	
	public calculations() {
		
	}
	public calculations(User username, Drink drink) {
		this.username = username;
		this.drink = drink; 
		
	}
	
	public double calculateBAC() {
		
		
		return 0; 
	}
	


}
*/
