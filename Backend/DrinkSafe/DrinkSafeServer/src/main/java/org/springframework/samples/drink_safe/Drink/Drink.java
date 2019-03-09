package org.springframework.samples.drink_safe.Drink;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.samples.drink_safe.user.User;

@Entity // the @Entity annotation tells the complier that this is a Database mapped
		// object
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
	@JoinColumn(name = "fkuser", nullable = false)
	private User fkuser;

	
	public Drink() {
		
	}
	public Drink(String drinkid, int alcPercent, int volume, User fkuser) {
		this.drinkid = drinkid;
		this.alcpercent = alcPercent;
		this.volume = volume;
		this.fkuser=fkuser;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}
	public String getFkuser() {
		return fkuser.getUsername();
	}
	public void setFkuser(User fkuser) {
		this.fkuser = fkuser;
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
	public String toString()
	{
		String returner = "";
		returner += "Drink = "+ getDrinkid();
		returner += " Alcohol Percent = "+getAlcpercent();
		returner += " Volume = " + getVolume();
		returner += " User = " + getFkuser();
		return returner;
	}


}
