package org.springframework.samples.drink_safe.user;

import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.Id;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

public class Group {

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
	
	
}
