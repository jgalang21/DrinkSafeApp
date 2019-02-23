package org.springframework.samples.drink_safe.friend;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.samples.drink_safe.user.User;


@Entity // the @Entity annotation tells the complier that this is a Database mapped object
@Table(name = "friend") // this tells the complier that this class is mapped to the table called "friend" in the database
public class friend{
	
	
    @Column(name = "user1")
    @NotFound(action = NotFoundAction.IGNORE)
	private User user1;
    
    @Column(name = "user2")
    @NotFound(action = NotFoundAction.IGNORE)
	private User user2;
 

	public friend(User user1, User user2, boolean areFriends) {
		this.user1 = user1;
		this.user2 = user2; 
		
	}
	
	
	public boolean areFriends() {
		if(user1.getFriends().contains(user2) && user2.getFriends().contains(user1)) {
			return true; 
		}
		return false;
	}
	
	public void acceptReq() {
		
	}
	
	
	


	
}
