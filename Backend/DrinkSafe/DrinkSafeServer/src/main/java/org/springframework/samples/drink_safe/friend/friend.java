package org.springframework.samples.drink_safe.friend;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * The friend class
 * 
 * @author Jeremy and Nick
 *
 */
@Entity
@Table(name = "friend")
public class friend {

	@Id
	@Column(name = "sentfrom")
	@NotFound(action = NotFoundAction.IGNORE)
	private String sentfrom;

	@Column(name = "sentto")
	@NotFound(action = NotFoundAction.IGNORE)
	private String sentto;

	public friend() {

	}

	public friend(String u1, String u2) {
		sentfrom = u1;
		sentto = u2;
	}

	/**
	 * @return the user who sent the request
	 */
	public String getSentfrom() {
		return sentfrom;
	}
	
	
	/**
	 * Set the user who sends the request
	 * @param sentfrom - a user
	 */
	public void setSentfrom(String sentfrom) {
		this.sentfrom = sentfrom;
	}

	/**
	 * @return - the person receiving the request
	 */
	public String getSentto() {
		return sentto;
	}

	/**
	 * Sets the user receiving the friend request
	 * @param sentto - the username
	 */
	public void setSentto(String sentto) {
		this.sentto = sentto;
	}
}
