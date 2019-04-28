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

/**
 * The user class that encompasses most of our app's functionality
 * 
 * @author Jeremy and Nick
 *
 */
@Entity // the @Entity annotation tells the complier that this is a Database mapped
		// object
@Table(name = "user") // this tells the complier that this class is mapped to the table called "user"
						// in the database
public class User {
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
	@JoinTable(name = "friend", joinColumns = @JoinColumn(name = "sentfrom"), inverseJoinColumns = @JoinColumn(name = "sentto"))
	private Set<User> friends = new HashSet<User>();
	@ManyToMany(mappedBy = "friends")
	private Set<User> befriended = new HashSet<User>();

	@OneToMany(mappedBy = "fkuser", cascade = CascadeType.ALL)
	private Set<Drink> drinks = new HashSet<Drink>();

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinTable(name = "drink_time", joinColumns = { @JoinColumn(name = "dtusername") }, inverseJoinColumns = {
			@JoinColumn(name = "tid") })
	time user_time;

	@Column(name = "bac")
	@NotFound(action = NotFoundAction.IGNORE)
	private double BAC;

	/**
	 * The user constructor class
	 * 
	 * @param username
	 * @param name
	 * @param password
	 * @param height
	 * @param weight
	 * @param gender
	 * @param guestStatus
	 */
	public User(String username, String name, String password, int height, int weight, int gender, int guestStatus) {
		this.username = username;
		this.name = name;
		this.password = password;
		this.height = height;
		this.weight = weight;
		this.gender = gender;
		this.guestStatus = guestStatus;
		this.BAC = 0;

	}

	public User() {
	}

	public static void create(User newUser) {

	}

	/**
	 * 
	 * @return - the person's registered username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * pls god yml work please
	 */
	
	/**
	 * Set or change a person's username
	 * @param username - the desired username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return - the User's password as a string
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password - a User's password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return height - the user's height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set the user's height
	 * @param height - the user's height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Gets the user's weight
	 * @return weight - the user's weight as an integer
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Sets the user's weight
	 * @param weight- the user's weight as an integer
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * @return gender - the user's gender
	 */
	public int getGender() {
		return gender;
	}

	/**
	 * @param gender - the user's gender, 0 = male, 1 = female
	 */
	public void Gender(int gender) {
		this.gender = gender;
	}

	/**
	 * 
	 * @return whether the user is sober or not, 0 = sober, 1 = not sober;
	 */
	public int getGuestStatus() {
		return guestStatus;
	}

	/**
	 * Sets if the user is sober or not
	 * @param guest_status - boolean whether the user is sober or not
	 */
	public void setGuestStatus(int guest_status) {
		this.guestStatus = guest_status;
	}

	/**
	 * Sets the user's gender
	 * @param gender - the gender of the user
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}

	/**
	 * 
	 * @return a list of all the user's friends as a string
	 */
	public String getFriends() {
		String returner = "";
		for (User u : friends)
			returner += u.getUsername() + " ";
		return returner;

	}

	/**
	 * 
	 * @param friends - a list of friends to modify
	 */
	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}

	/**
	 * @return friends - a modifiable set of friends
	 */
	public Set<User> toModifyFriends() {
		return friends;
	}

	/**
	 * @return get's a list of friends
	 */
	public String getBefriended() {
		String returner = "";
		for (User u : befriended)
			returner += u.getUsername() + " ";
		return returner;
	}

	/**
	 * @return sets a list of friends
	 */
	public void setBefriended(Set<User> befriended) {
		this.befriended = befriended;
	}

	/**
	 * 
	 * @return a list of friends that can be modified
	 */
	public Set<User> toModifyBefriended() {
		return befriended;
	}

	/**
	 * @return the user's time
	 */
	public time getUser_time() {
		return user_time;
	}

	/**
	 * @param user_time - sets the user's time
	 */
	public void setUser_time(time user_time) {
		this.user_time = user_time;
	}

	/**
	 * @return returner - all the drinks as a string
	 */
	public String getDrinks() {
		String returner = "";
		for (Drink d : drinks)
			returner += d.getDid() + " " + d.getDrinkid() + " ";
		return returner;
	}

	public Set<Drink> toModifyDrinks() {
		return drinks;
	}

	public void setDrinks(Set<Drink> drinks) {
		this.drinks = drinks;
	}

	/**
	 * Gives the user a drink
	 * 
	 * @param drink - the drink to give the user
	 */
	public void giveDrink(Drink drink) {
		drinks.add(drink);
		this.setGuestStatus(1);
	}

	/**
	 * 
	 * @return - the user's name
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return - the user's BAC
	 */
	public double getBAC() {
		return BAC;
	}

	public void setBAC(double bAC) {
		BAC = bAC;
	}

}
