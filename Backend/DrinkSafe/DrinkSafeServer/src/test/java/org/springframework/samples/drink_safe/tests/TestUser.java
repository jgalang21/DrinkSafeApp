package org.springframework.samples.drink_safe.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.samples.drink_safe.Drink.Drink;
import org.springframework.samples.drink_safe.user.User;
import org.springframework.samples.drink_safe.user.UserRepository;
import org.springframework.samples.drink_safe.user.UserService;

public class TestUser {

	@InjectMocks
	UserService userServe;
	
	@Mock
	UserRepository userRepo;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	//Testing if the user credentials match
	@Test
	public void testCredentials() {
		//public User(String username, String name, String password, int height, int weight,int gender, int guestStatus)
		User x = new User("jman22", "John", "Dogs", 48, 222, 0, 0);
		
		assertEquals("jman22", x.getUsername());
		assertEquals("John", x.getName());
		assertEquals("Dogs", x.getPassword());
		assertEquals(48, x.getHeight());
		assertEquals(222, x.getWeight());
		assertEquals(0, x.getGender());
		assertEquals(0, x.getGuestStatus());
		
		
	}
	
	

	@Test
	public void testDrink() {
		//public Drink(String drinkid, int alcPercent, int volume, User fkuser)
		User x = new User("jman22", "John", "Dogs", 48, 222, 0, 0);
		Drink r = new Drink("Grey Goose", 10, 15, x); 
		
		x.giveDrink(r);
		
		Mockito.verify(x).giveDrink(r);
		
		assertEquals("Grey Goose", x.getDrinks());
	}
	
	
	//does not like multiple instances of the same drink
	@Test
	public void testDrink2() {
		//public Drink(String drinkid, int alcPercent, int volume, User fkuser)
		User x = new User("jman22", "John", "Dogs", 48, 222, 0, 0);
		Drink r = new Drink("Grey Goose", 10, 15, x); 
		
		x.giveDrink(r);
		x.giveDrink(r);
		
		assertEquals("Grey Goose Grey Goose ", x.getDrinks());
	}
	
	
	public void testDrinkCredentials() {
		User x = new User("jman22", "John", "Dogs", 48, 222, 0, 0);
		Drink r = new Drink("Grey Goose", 10, 15, x); 
		
		
	//	assertEquals("Grey Goose", )
	}
	
	
}
