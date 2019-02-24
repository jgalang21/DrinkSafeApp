package org.springframework.samples.drink_safe.friend;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.samples.drink_safe.user.User;

@Entity
@Table(name="friend")
public class friend {
	@Id
    @Column(name = "u1username")
    @NotFound(action = NotFoundAction.IGNORE)
	private String u1username;
	
    
    @Column(name = "friend")
    @NotFound(action = NotFoundAction.IGNORE)
	private String friend;
    public friend()
    {
    	
    }
    public friend(String u1, String u2)
    {
    	u1username=u1;
    	friend=u2;
    }

	public String getU1username() {
		return u1username;
	}


	public void setU1username(String u1username) {
		this.u1username = u1username;
	}


	public String getFriend() {
		return friend;
	}


	public void setFriend(String friend) {
		this.friend = friend;
	}

}
