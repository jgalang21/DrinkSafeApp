package org.springframework.samples.drink_safe.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
	public void testUserCredentials() {
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
	public void testUserSetters() {
		User x = new User("jman22", "John", "Dogs", 48, 222, 0, 0);
		
		x.setUsername("Kdog");
		x.setName("Carl");
		x.setPassword("Cats");
		x.setHeight(49);
		x.setWeight(223);
		x.setGender(1);
		x.setGuestStatus(0);
		
		
		assertEquals("Kdog", x.getUsername());
		assertEquals("Carl", x.getName());
		assertEquals("Cats", x.getPassword());
		assertEquals(49, x.getHeight());
		assertEquals(223, x.getWeight());
		assertEquals(1, x.getGender());
		assertEquals(0, x.getGuestStatus());
		
	}

	
	@Test
	public void testUserCredentialsMock() {
		User x = mock(User.class);//new User("jman22", "John", "Dogs", 48, 222, 0, 0);

		when(x.getUsername()).thenReturn("Snake21");
		when(x.getName()).thenReturn("John");
		when(x.getPassword()).thenReturn("Cobra");
		when(x.getHeight()).thenReturn(57);
		when(x.getWeight()).thenReturn(170);
		when(x.getGender()).thenReturn(0);
		when(x.getGuestStatus()).thenReturn(1);

		
		assertEquals("Snake21", x.getUsername());
		assertEquals("John", x.getName());
		assertEquals("Cobra", x.getPassword());
		assertEquals(57, x.getHeight());
		assertEquals(170, x.getWeight());
		assertEquals(0, x.getGender());
		assertEquals(1, x.getGuestStatus());
	
	}
	
	@Test
	public void testDrinkCredentials() {
		//public Drink(int did, String drinkid, int alcPercent, int volume, User fkuser) {
		User r = mock(User.class);
		Drink w = new Drink(0, "Whiskey", 10, 23, r);
		
		assertEquals(0, w.getDid());
		assertEquals("Whiskey", w.getDrinkid());
		assertEquals(10, w.getAlcpercent());
		assertEquals(23, w.getVolume());
		assertEquals(null, w.getFkuser());
		
		
	}
	
	@Test
	public void testMockDrinkCredentials() {
		//public Drink(int did, String drinkid, int alcPercent, int volume, User fkuser) {
		User r = mock(User.class);
		Drink w = mock(Drink.class);
		
		
		when(w.getDid()).thenReturn(0);
		when(w.getDrinkid()).thenReturn("Whiskey");
		when(w.getAlcpercent()).thenReturn(10);
		when(w.getVolume()).thenReturn(23);
		when(w.getFkuser()).thenReturn("lmaokai");
		
		assertEquals(0, w.getDid());
		assertEquals("Whiskey", w.getDrinkid());
		assertEquals(10, w.getAlcpercent());
		assertEquals(23, w.getVolume());
		assertEquals("lmaokai", w.getFkuser());
		
		
	}
	
	
	
	
	
	
	
	//// --------------------------------------------------JEREMY'S TESTS ---------------------------
	
	
	
	/// ---------------------------------------------------NICK'S TESTS------------------------------
	@Test
	public void testDrinkAdd() throws Exception {
		Drink x = mock(Drink.class);
		
		
		when(x.getDid()).thenReturn(0);
		when(x.getDrinkid()).thenReturn("Beer");
		when(x.getAlcpercent()).thenReturn(4);
		when(x.getVolume()).thenReturn(18);
		when(x.getFkuser()).thenReturn("BigHAAS");


	}
}
