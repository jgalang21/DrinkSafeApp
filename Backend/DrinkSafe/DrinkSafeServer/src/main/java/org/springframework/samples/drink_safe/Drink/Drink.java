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
    @Column(name = "drinkId") 
    @NotFound(action = NotFoundAction.IGNORE)
	private String drinkId;
	
    
    @Column(name = "alcPercent")
    @NotFound(action = NotFoundAction.IGNORE)
	private int alcPercent;
    
    @Column(name = "volume")
    @NotFound(action = NotFoundAction.IGNORE)
	private int volume;
    
    @ManyToOne
    @JoinColumn(name = "cusername",nullable =false)
    private User user_td;
    
    
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
    
	@ManyToMany(mappedBy="drinks")
	private Set<User> befriended = new HashSet<User>();





	public Set<User> getBefriended() {
		return befriended;
	}


	public void setBefriended(Set<User> befriended) {
		this.befriended = befriended;
	}


	public User getUser_td() {
		return user_td;
	}


	public void setUser_td(User user_td) {
		this.user_td = user_td;
	}

    
   

}
