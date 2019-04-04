package org.springframework.samples.drink_safe.user;

import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="group")
public class Group {
	@Id
    @Column(name = "gm")
    @NotFound(action = NotFoundAction.IGNORE)
	private String gm;
	
    
    @Column(name = "m")
    @NotFound(action = NotFoundAction.IGNORE)
	private String m;
    public Group()
    {
    	
    }
    public Group(String gm, String ,)
    {
    	this.gm=u1;
    	sentto=u2;
    }

	public String getGroup() {
		String returner = "";
		for(User u: group)
			returner+= u.getUsername()+ " ";
		return returner;
	}

	public void setGroup(HashSet<User> group) {
		this.group = group;
	}
	
	
}
