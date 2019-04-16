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

/**
 * The Drink object class
 * 
 * @author Jeremy and Nick
 *
 */
@Entity
@Table(name = "drink")
public class Drink {

	@Id
	@Column(name = "did")
	@NotFound(action = NotFoundAction.IGNORE)
	private int did;

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

	/**
	 * Constructor
	 * 
	 * @param did        - the drink id, random integer that uniquely identifies
	 *                   each drink
	 * @param drinkid    - the name of the drink
	 * @param alcPercent - the percent alcohol contents
	 * @param volume     - the volume of alcohol in the drink
	 * @param fkuser     - the name of the user that consumed the drink
	 */
	public Drink(int did, String drinkid, int alcPercent, int volume, User fkuser) {
		this.did = did;
		this.drinkid = drinkid;
		this.alcpercent = alcPercent;
		this.volume = volume;
		this.fkuser = fkuser;
	}

	// the next few methods are just simply getters and setters
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

	public String toString() {
		String returner = "";
		returner += "Drink = " + getDrinkid();
		returner += " Alcohol Percent = " + getAlcpercent();
		returner += " Volume = " + getVolume();
		returner += " User = " + getFkuser();
		return returner;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

}
