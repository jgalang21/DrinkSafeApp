package org.springframework.samples.drink_safe.Drink;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity // the @Entity annotation tells the complier that this is a Database mapped object
@Table(name = "Drink")
public class Drink {
	
	@Id
    @Column(name = "DrinkId")
    @NotFound(action = NotFoundAction.IGNORE)
	private String DrinkId;
	
    
    @Column(name = "alcPercent")
    @NotFound(action = NotFoundAction.IGNORE)
	private int alcPercent;
    
    
    public Drink(String drinkid, int alcPercent) {
    	this.DrinkId = drinkid;
    	this.alcPercent = alcPercent;
    }
    
    
    public String getDrink() {
    	return DrinkId; 
    }
    
    public int percentage() {
    	return alcPercent;
    }
    
   

}
