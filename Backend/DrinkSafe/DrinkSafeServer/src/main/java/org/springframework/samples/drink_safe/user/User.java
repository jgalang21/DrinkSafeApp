package org.springframework.samples.drink_safe.user;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


@Entity // the @Entity annotation tells the complier that this is a Database mapped object
@Table(name = "User") // this tells the complier that this class is mapped to the table called "user" in the database
public class User{
	@Id
    @Column(name = "username")
    @NotFound(action = NotFoundAction.IGNORE)
	private String username;
	
    @Column(name = "password")
    @NotFound(action = NotFoundAction.IGNORE)
	private String password;
    

    @Column(name = "height")
    @NotFound(action = NotFoundAction.IGNORE)
	private int height;
    
    @Column(name = "weight")
    @NotFound(action = NotFoundAction.IGNORE)
	private int weight; 
    
    @Column(name = "gender")
    @NotFound(action = NotFoundAction.IGNORE)
	private int gender; 
    
    @Column(name = "guest_status")
    @NotFound(action = NotFoundAction.IGNORE)
	private int guestStatus;
    
    
    ArrayList<User> friends = new ArrayList<User>();

	public User(String username, String password, int height, int weight,int gender, int guestStatus, ArrayList<User> friends) {
		this.username = username;
		this.password = password;
		this.height=height;
		this.weight=weight;
		this.gender=gender;
		this.guestStatus=guestStatus;
		this.friends = friends;
	}
	public User()
	{}

	public static void create(User newUser) {
		
	}
	
	public ArrayList<User> getFriends(){
		return friends;
	}
	
	public int numFriends() {
		return friends.size();
	}
	
	public boolean checkFriendship(User name) {
		if(friends.contains(name) && name.friends.contains(this)) {
			return true;
		}
		return false;
		
	}
	
	public void addFriend(User name){
		friends.add(name);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getGender() {
		return gender;
	}

	public void Gender(int gender) {
		this.gender = gender;
	}

	public int getGuestStatus() {
		return guestStatus;
	}

	public void setGuestStatus(int guest_status) {
		this.guestStatus = guest_status;
	}
}
