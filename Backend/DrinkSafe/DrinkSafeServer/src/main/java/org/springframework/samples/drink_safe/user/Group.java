package org.springframework.samples.drink_safe.user;

import java.util.HashSet;

public class Group {
	
	HashSet<User> group = new HashSet<User>();

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
