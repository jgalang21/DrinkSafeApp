package org.springframework.samples.drink_safe.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.samples.drink_safe.Drink.Drink;
import org.springframework.samples.drink_safe.time.time;

@Entity // the @Entity annotation tells the complier that this is a Database mapped object
@Table(name = "user") // this tells the complier that this class is mapped to the table called "user" in the database
public class User{
	@Id
    @Column(name = "username")
    @NotFound(action = NotFoundAction.IGNORE)
	private String username;
	
    @Column(name = "name")
    @NotFound(action = NotFoundAction.IGNORE)
	private String name;
	
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
        joinColumns = @JoinColumn(name = "sentfrom"),
    	inverseJoinColumns = @JoinColumn(name = "sentto"))
    private Set<User> friends = new HashSet<User>();
    @ManyToMany(mappedBy = "friends")
    private Set<User> befriended = new HashSet<User>();
    
    
    @OneToMany(mappedBy = "fkuser", cascade = CascadeType.ALL)
    private Set<Drink> drinks= new HashSet<Drink>();
    

    @OneToOne(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "drink_time", 
        joinColumns = { @JoinColumn(name = "dtusername") }, 
        inverseJoinColumns = { @JoinColumn(name = "tid") }
    )
    time user_time; 
    

	public User(String username, String name, String password, int height, int weight,int gender, int guestStatus) {
		this.username = username;
		this.name = name;
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
	public String getFriends() {
		String returner ="";
		for(User u: friends)
			returner+=u.getUsername() + " ";
		return returner;
			
	}
	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}
	public Set<User> toModifyFriends()
	{
		return friends;
	}
	public String getBefriended() {
		String returner ="";
		for(User u: befriended)
			returner+=u.getUsername() + " ";
		return returner;
	}
	public void setBefriended(Set<User> befriended) {
		this.befriended = befriended;
	}
	public Set<User> toModifyBefriended()
	{
		return befriended;
	}
	public String toString()
	{
		String friends_list="";
		for(User i: friends)
			friends_list+=i.getUsername()+", ";
		String befriended_list="";
		for(User i:befriended)
			befriended_list+=i.getUsername() +", ";
		String drink_list="";
		for(Drink d: drinks)
			drink_list+=d.getDrinkid() +", ";
		String timer = "";
		if(! (user_time == null))
		{
			timer+=(user_time.getTime_finish() -user_time.getTime_start());
		}
		String returner ="";
		returner += "Username: " + getUsername()+"\n";
		returner += "Name: " + getName() + "\n";
		returner += "Password: " + getPassword()+"\n";
		returner += "Height: " + getHeight()+"\n";
		returner += "Weight: " + getWeight()+"\n";
		returner += "Gender: " + getGender()+"\n";
		returner += "Guest Status: " + getGuestStatus()+"\n";
		returner += "Friends List: "+friends_list+ "\n";
		returner += "Befriened List: "+befriended_list+"\n";
		returner += "Drink List: "+drink_list+"\n";
		returner += "timer: "+timer;
		return returner;
	}
	public time getUser_time() {
		return user_time;
	}
	public void setUser_time(time user_time) {
		this.user_time = user_time;
	}
	
	public Set<Drink> getDrinks() {
		return drinks;
	}
	public void setDrinks(Set<Drink> drinks) {
		this.drinks = drinks;
	}
	public void giveDrink(Drink drink) {
		drinks.add(drink);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
