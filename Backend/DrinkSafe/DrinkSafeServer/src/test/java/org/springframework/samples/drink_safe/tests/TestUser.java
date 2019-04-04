package org.springframework.samples.drink_safe.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
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
import org.springframework.samples.drink_safe.Drink.DrinkController;
import org.springframework.samples.drink_safe.user.User;
import org.springframework.samples.drink_safe.user.UserController;
import org.springframework.samples.drink_safe.user.UserRepository;
import org.springframework.samples.drink_safe.user.UserService;
import org.springframework.web.bind.annotation.PathVariable;

public class TestUser {

	@InjectMocks
	UserService userServe;
	
	@Mock
	UserRepository userRepo;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		userServe = mock(UserService.class);
		userRepo = mock(UserRepository.class);
		
	}
	
	//// --------------------------------------------------JEREMY'S TESTS ---------------------------
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
	public void testCredentialsMock() {
		//public User(String username, String name, String password, int height, int weight,int gender, int guestStatus)
		User x = mock(User.class);
		
		
		when(x.getUsername()).thenReturn("Tim21");
		when(x.getName()).thenReturn("Tim");
		when(x.getPassword()).thenReturn("Rajon Rondo");
		when(x.getHeight()).thenReturn(33);
		when(x.getWeight()).thenReturn(100);
		when(x.getGender()).thenReturn(0);
		when(x.getGuestStatus()).thenReturn(1);
		
		
		
		
		
		
	}
	/*
	@Test
	public void testDrinkCredentials() {
		User x = new User("jman22", "John", "Dogs", 48, 222, 0, 0);
		Drink r = new Drink("Grey Goose", 10, 15, x); 
		
		
		assertEquals("Grey Goose", r.getDrinkid());
		assertEquals(10, r.getAlcpercent());
		assertEquals(15, r.getVolume());
		assertEquals(x.getUsername(), r.getFkuser());
	}
	

	@Test
	public void testDrink() {
		//public Drink(String drinkid, int alcPercent, int volume, User fkuser)
		User x = new User("jman22", "John", "Dogs", 48, 222, 0, 0);
		Drink r = new Drink("Grey Goose", 10, 15, x); 
		
		x.giveDrink(r);
		
		assertEquals("Grey Goose", x.getDrinks());
	}
	
	
	//does not like multiple instances of the same drink
	@Test
	public void testDrink2() {
		//public Drink(String drinkid, int alcPercent, int volume, User fkuser)
		User x = mock(User.class);
		Drink r = new Drink("Grey Goose", 10, 15, x); 

		
		//when(x.giveDrink(r)).thenReturn("Grey Goose");
		x.giveDrink(r);
		x.giveDrink(r);
		
		assertEquals("Grey Goose Grey Goose ", x.getDrinks());
	}
	
	
	//this tests that the drink r has been added twice, and doesn't include any other drinks.
	//this essentially counts how many instances of a certain drink
	@Test
	public void testDrink3() {
		
		User y = mock(User.class);
		Drink r = new Drink("Grey Goose", 10, 15, y); 
		Drink r2 = mock(Drink.class);
	
		y.giveDrink(r);
		y.giveDrink(r);
		y.giveDrink(r2);
		
		verify(y, times(2)).giveDrink(r);
	

	}
	
	@Test
	public void testDrink4() {
		
		User y = mock(User.class);
		Drink r = new Drink("Grey Goose", 10, 15, y); 
		Drink r2 = mock(Drink.class);
	
		when(y.getGender()).thenReturn(0);
		//when(y.giveDrink(r)).thenReturn("Done");
		y.giveDrink(r);
		y.giveDrink(r);
		y.giveDrink(r2);
		
		assertEquals(y.getGender(), 0);
		
		//verify(y, times(2)).giveDrink(r);
	

	}
	
	
	*/
	
	//// --------------------------------------------------JEREMY'S TESTS ---------------------------
	/// ---------------------------------------------------NICK'S TESTS------------------------------
	@Test
	public void testDrinkAdd() {
		DrinkController x = mock(DrinkController.class);
		UserController m = mock(UserController.class);
		m.saveUser("username","name","password",70,200,1,0);
		x.addDrink("Beer", 4, 18, "username");
		User a = m.findUserbyID("username");
		assertTrue(a.getDrinks().equals("Beer"));

	}
	
}
