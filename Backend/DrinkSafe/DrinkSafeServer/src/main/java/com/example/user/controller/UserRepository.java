package com.example.user.controller;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Data;

@Data
@Entity // the @Entity annotation tells the complier that this is a Database mapped object
@Table(name = "user") // this tells the complier that this class is mapped to the table called "user" in the database
public class UserRepository{
    @Column(name = "username")
    @NotFound(action = NotFoundAction.IGNORE)
	private String username;
    @Column(name = "password")
    @NotFound(action = NotFoundAction.IGNORE)
	private String password;
	private int height;
    @Column(name = "height")
    @NotFound(action = NotFoundAction.IGNORE)
	private int weight; 
    @Column(name = "guest_status")
    @NotFound(action = NotFoundAction.IGNORE)
	private int guest_status;

	public UserRepository(String username, String password, int height, int weight, int guest_status) {
		this.username = username;
		this.password = password;
		this.height=height;
		this.weight=weight;
		this.guest_status=guest_status;
	}

	public static void create(User newUser) {
		// TODO Auto-generated method stub
		
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

	public int getGuest_status() {
		return guest_status;
	}

	public void setGuest_status(int guest_status) {
		this.guest_status = guest_status;
	}
}