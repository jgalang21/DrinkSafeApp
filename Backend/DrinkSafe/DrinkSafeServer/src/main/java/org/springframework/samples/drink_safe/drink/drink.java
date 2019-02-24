package org.springframework.samples.drink_safe.drink;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity // the @Entity annotation tells the complier that this is a Database mapped object
@Table(name = "drink")
public class drink {
	
	@Id
    @Column(name = "DrinkId")
    @NotFound(action = NotFoundAction.IGNORE)
	private String DrinkId;
	
    
    @Column(name = "alcPercent")
    @NotFound(action = NotFoundAction.IGNORE)
	private int alcPercent;
    
    @Column(name = "volume")
    @NotFound(action = NotFoundAction.IGNORE)
	private int volume;
    
    
    public drink(String drinkid, int alcPercent,int volume) {
    	this.DrinkId = drinkid;
    	this.alcPercent = alcPercent;
    	this.volume = volume;
    }


	public String getDrinkId() {
		return DrinkId;
	}


	public void setDrinkId(String drinkId) {
		DrinkId = drinkId;
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
