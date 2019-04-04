package org.springframework.samples.drink_safe.user;

import java.util.HashSet;
<<<<<<< HEAD

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="group")
=======
import javax.persistence.Column;
import javax.persistence.Id;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

>>>>>>> 0a2fb7890052d5564aff76271231ad0620c2b66b
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
	

<<<<<<< HEAD
=======
	@Id
	@Column(name = "groupName")
	@NotFound(action = NotFoundAction.IGNORE)
	private String groupName;


	HashSet<User> group = new HashSet<User>();

	public Group(String gName) {
		this.groupName = gName; 
	}

	public Group() {
	}

	
	public void addMember(User name) {
		group.add(name);
	}
	
	public void removeMember(User name) {
		group.remove(name);
	}
	
>>>>>>> 0a2fb7890052d5564aff76271231ad0620c2b66b
	
}
