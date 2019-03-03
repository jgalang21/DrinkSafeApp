package org.springframework.samples.drink_safe.Drink;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.samples.drink_safe.user.User;

@Entity // the @Entity annotation tells the complier that this is a Database mapped object
@Table(name = "drink")
public class Drink {
	
	@Id
    @Column(name = "drinkid") 
    @NotFound(action = NotFoundAction.IGNORE)
	private String drinkid;
	
    
    @Column(name = "alcpercent")
    @NotFound(action = NotFoundAction.IGNORE)
	private int alcpercent;
    
    @Column(name = "volume")
    @NotFound(action = NotFoundAction.IGNORE)
	private int volume;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_user",nullable =false)
    private User user;
    
    
    public Drink(String drinkid, int alcPercent,int volume) {
    	this.drinkid = drinkid;
    	this.alcpercent = alcPercent;
    	this.volume = volume;
    }







	public int getVolume() {
		return volume;
	}


	public void setVolume(int volume) {
		this.volume = volume;
	}

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}




	public String getDrinkid() {
		return drinkid;
	}




	public void setDrinkid(String drinkid) {
		this.drinkid = drinkid;
	}







	public int getAlcpercent() {
		return alcpercent;
	}







	public void setAlcpercent(int alcpercent) {
		this.alcpercent = alcpercent;
	}



}
