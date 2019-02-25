package org.springframework.samples.drink_safe.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


@Entity // the @Entity annotation tells the complier that this is a Database mapped object
@Table(name = "user") // this tells the complier that this class is mapped to the table called "user" in the database
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
  
    @ManyToMany
    @JoinTable(name = "friend", 
        joinColumns = @JoinColumn(name = "u1username"), 
        inverseJoinColumns = @JoinColumn(name = "friend"))
    protected List<User> friends = new ArrayList<User>();
    @ManyToMany(mappedBy = "friends")
    protected List<User> befriended = new ArrayList<User>();
  

    

	public User(String username, String password, int height, int weight,int gender, int guestStatus) {
		this.username = username;
		this.password = password;
		this.height=height;
		this.weight=weight;
		this.gender=gender;
		this.guestStatus=guestStatus;
		
		
	}
	public User()
	{}

	public static void create(User newUser) {
		
	}
	
	//deleted the check friends in user since that is already in friends 
	
	
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
	public void setGender(int gender) {
		this.gender = gender;
	}
	public List getFriends() {
		return friends;
	}
	public void setFriends(List friends) {
		this.friends = friends;
	}
	public List<User> getBefriended() {
		return befriended;
	}
	public void setBefriended(List<User> befriended) {
		this.befriended = befriended;
	}
	public String toString()
	{
		String friends_list="";
		for(User i: friends)
			friends_list+=i.getUsername()+", ";
		String befriended_list="";
		for(User i:befriended)
			befriended_list+=i.getUsername() +", ";
		String returner ="";
		returner += "Username: " + getUsername()+"\n";
		returner += "Password: " + getPassword()+"\n";
		returner += "Height: " + getHeight()+"\n";
		returner += "Weight: " + getWeight()+"\n";
		returner += "Gender: " + getGender()+"\n";
		returner += "Guest Status: " + getGuestStatus()+"\n";
		returner += "Friends List: "+friends_list+ "\n";
		returner += "Befriened List: "+befriended_list;
		return returner;
	}

}
