package org.springframework.samples.drink_safe.buddies;

import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.samples.drink_safe.friend.friend;

@Entity
@Table(name="buddies")
public class buddies {
	@Id
    @Column(name = "inviter")
    @NotFound(action = NotFoundAction.IGNORE)
	private String inviter;
	
    
    @Column(name = "invitee")
    @NotFound(action = NotFoundAction.IGNORE)
	private String invitee;
    
    public buddies()
    {
    	
    }
    public buddies(String gm, String m)
    {
    	this.inviter = gm;
    	this.invitee =m;
    }
	public String getInviter() {
		return inviter;
	}
	public void setInviter(String inviter) {
		this.inviter = inviter;
	}
	public String getInvitee() {
		return invitee;
	}
	public void setInvitee(String invitee) {
		this.invitee = invitee;
	}

	public static void create(buddies new_rel) {
		// TODO Auto-generated method stub
	}
	
	
}
