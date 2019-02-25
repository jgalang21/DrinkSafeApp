package org.springframework.samples.drink_safe.Drink;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
	private String drinkId;
	
    
    @Column(name = "alcPercent")
    @NotFound(action = NotFoundAction.IGNORE)
	private int alcPercent;
    
    @Column(name = "volume")
    @NotFound(action = NotFoundAction.IGNORE)
	private int volume;
    
    
    public Drink(String drinkid, int alcPercent,int volume) {
    	this.drinkId = drinkid;
    	this.alcPercent = alcPercent;
    	this.volume = volume;
    }


	public String getDrinkId() {
		return drinkId;
	}


	public void setDrinkId(String drinkId) {
		this.drinkId = drinkId;
	}


	public int getAlcPercent() {
		return alcPercent;
	}


	public void setAlcPercent(int alcPercent) {
		this.alcPercent = alcPercent;
	}


	public int getVolume() {
		return volume;
	}


	public void setVolume(int volume) {
		this.volume = volume;
	}
    
    

    
   

}
