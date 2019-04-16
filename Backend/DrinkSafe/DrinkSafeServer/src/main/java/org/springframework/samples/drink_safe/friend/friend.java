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

	public static void create(friend new_rel) {
		// TODO Auto-generated method stub
	}

	public String getSentfrom() {
		return sentfrom;
	}

	public void setSentfrom(String sentfrom) {
		this.sentfrom = sentfrom;
	}

	public String getSentto() {
		return sentto;
	}

	public void setSentto(String sentto) {
		this.sentto = sentto;
	}
}
