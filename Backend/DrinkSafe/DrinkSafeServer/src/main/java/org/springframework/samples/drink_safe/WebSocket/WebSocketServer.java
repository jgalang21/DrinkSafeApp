package org.springframework.samples.drink_safe.WebSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.drink_safe.user.User;
import org.springframework.samples.drink_safe.user.UserController;
import org.springframework.samples.drink_safe.user.UserRepository;
import org.springframework.samples.drink_safe.user.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@ServerEndpoint("/WebSocket/{username}")
@Component
public class WebSocketServer {
	
	@Autowired
	UserRepository userRepo;

		@RequestMapping(method = RequestMethod.GET, path= "/users/new/{username}/{name}/{password}/{height}/{weight}/{gender}/{guestStatus}")
		public void saveUser(@PathVariable("username") String username,@PathVariable("name") String name,@PathVariable("password") String password,@PathVariable("height") int height,@PathVariable("weight") int weight,@PathVariable("gender") int gender, @PathVariable("guestStatus") int guestStatus)
		{
			User user = new User(username,name,password,height,weight,gender,guestStatus);
			userRepo.save(user);
			logger.info("saving new user: " + user.getUsername());
		}
		
		@RequestMapping(method = RequestMethod.GET, path= "/users")
		public List<User> returnAllUsers(){
			logger.info("Displaying all users");
	        List<User> results = (List<User>) userRepo.findAll();
	        logger.info("Number of users:"  + results.size());
	        return results;
			
		}
		
		@RequestMapping(method = RequestMethod.GET, path="/users/find/id/{userId}")
		public User findUserbyID(@PathVariable("userId") String id) {
			logger.info("Finding user: "+id);
	        User results = userRepo.findByUsername(id);
	        return results;
		}
		
		@RequestMapping(method = RequestMethod.GET, path="/users/find/gs/{guest_status}")
		public List<User> findByGuest_Status(@PathVariable("guest_status") int guest_status) {
			logger.info("Finding user: "+guest_status);
	        List<User> results = userRepo.findAllByGuestStatus(guest_status);
	        return results;
		}
		

		
		@RequestMapping(method = RequestMethod.GET, path="/users/friend/addFriends/{user1Id}/{user2Id}")
		public void addFriends(@PathVariable("user1Id") String user1,@PathVariable("user2Id") String user2) {
			User u = userRepo.findByUsername(user1);
			User u2 =userRepo.findByUsername(user2);
			u.toModifyFriends().add(u2);
			userRepo.save(u);
			logger.info(u.getUsername()+ " has added " + u2.getUsername() + " as a friend");
		}
		

		@RequestMapping(method = RequestMethod.GET, path="/users/friend/addGroup/{user1Id}/{user2Id}")
		public void addGroup(@PathVariable("user1Id") String user1,@PathVariable("user2Id") String user2) {
			User u = userRepo.findByUsername(user1);
			User u2 =userRepo.findByUsername(user2);
			u2.toModifyBuddies().add(u);
			userRepo.save(u2);
			logger.info(u.getUsername()+ " has added " + u2.getUsername() + " into their group");
		}
		
		@RequestMapping(method = RequestMethod.GET, path="/users/friend/leave/{userId}")
		public void leaveGroup(@PathVariable("userId") String user) {
			User u = userRepo.findByUsername(user);
			if(!u.toModifyBuddies().isEmpty()) {
				u.setInviter( new HashSet<User>());
			}
			else {
				java.util.Iterator<User> iter = u.toModifyInvitee().iterator();
				while(iter.hasNext())
				{
					User u2 = iter.next();
					u2.setInviter( new HashSet<User>());
				}
			}
			userRepo.save(u);
			logger.info(u.getUsername()+ " has removed themselves from the group");
		}
		
		@RequestMapping(method = RequestMethod.GET, path="/users/friend/getGroup/{userId}")
		public List<User> getGroup(@PathVariable("userId") String user) {
			User u = userRepo.findByUsername(user);
			User u2;
			if (!u.toModifyBuddies().isEmpty())
			{
				
				java.util.Iterator<User> iter = u.toModifyBuddies().iterator();
				u2 = iter.next();
				java.util.Iterator<User> iter2 = u2.toModifyInvitee().iterator();
				List<User> returner = new ArrayList<User>();
				returner.add(u2);
				while(iter2.hasNext())
					returner.add(iter2.next());
				return returner;
			}
			else
			{
				java.util.Iterator<User> iter2 = u.toModifyInvitee().iterator();
				List<User> returner = new ArrayList<User>();
				returner.add(u);
				while(iter2.hasNext())
					returner.add(iter2.next());
				return returner;
			}
		}
		
		
		
		@RequestMapping(method = RequestMethod.GET, path="/users/edit/weight/{userId}/{weight}")
		public void editWeight(@PathVariable("userId") String user,@PathVariable("weight") int newWeight)
		{
			User u = userRepo.findByUsername(user);
			u.setWeight(newWeight);
			userRepo.save(u);
			logger.info(u.getUsername()+ " has changed weight to " + newWeight);
		}
		
		@RequestMapping(method = RequestMethod.GET, path="/users/edit/height/{userId}/{height}")
		public void editHeight(@PathVariable("userId") String user,@PathVariable("height") int newHeight)
		{
			User u = userRepo.findByUsername(user);
			u.setHeight(newHeight);
			userRepo.save(u);
			logger.info(u.getUsername()+ " has changed weight to " + newHeight);
		}
		


		




	// Store all socket session and their corresponding username.
	private static Map<Session, String> sessionUsernameMap = new HashMap<>();
	private static Map<String, Session> usernameSessionMap = new HashMap<>();
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(WebSocketServer.class);


	UserController x = new UserController();
	UserService s = new UserService();

	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) throws IOException {
		logger.info("Entered into Open");

		sessionUsernameMap.put(session, username);
		usernameSessionMap.put(username, session);

		String message = "User:" + username + " has Joined the Chat";
		broadcast(message);

	}

	@OnMessage
	public void onMessage(Session session, String message) throws IOException {

		// Handle new messages
		logger.info("Entered into Message: Got Message:" + message);
		// User u = userRepo.findByUsername(sessionUsernameMap.get(session));
		String r = sessionUsernameMap.get(session);
		User u = findUserbyID(r);
		

		if (message.startsWith("@")) { // Direct message to a user using the format "@username <message>"

			String destUsername = message.split(" ")[0].substring(1); // don't do this in your code!
			sendMessageToPArticularUser(destUsername, "[DM] " + u.getUsername() + ": " + message);
			sendMessageToPArticularUser(u.getUsername(), "[DM] " + u.getUsername() + ": " + message);
		}

		if (message.equals("!help")) {
			broadcast("List of commands: \n" + "---------------\n" + "GROUP COMMANDS\n" + "---------------\n"
					+ "Create group: !group\n" + "List members: !get_members\n" + "Leave group: !leave\n"
					+ "Add member: !add [username]\n");

		}


		// if they aren't in a group, they shouldn't be able to run these commands

		if (!u.toModifyBuddies().isEmpty() && !u.toModifyInvitee().isEmpty()) {

			if (message.equals("!get_members")) {
				broadcast("List of members:");
				System.out.println(x.getGroup(u.getUsername()));
			}

			if (message.substring(0, 5).equals("!add ")) {

				if (u.toModifyBuddies().isEmpty()) {
					User u2 = findUserbyID(message.substring(6, message.length() - 1));

					addGroup(u.getUsername(), u2.getUsername());

				}

				if (message.substring(6, message.length() - 1).equals(u.getUsername())) {
					broadcast("You're already in the group!");
				} else {
					broadcast("User does not exist");
				}
			}

			if (message.equals("!leave")) {
				if (!u.toModifyBuddies().isEmpty()) {

				}
				broadcast(u.getUsername() + " has left the group.");
			} else {
				broadcast("You are not currently in a group.");
			}
		}

		else {// Message to whole chat

			broadcast(u.getUsername() + ": " + message);
		}

	}

	@OnClose
	public void onClose(Session session) throws IOException {
		logger.info("Entered into Close");

		User u = findUserbyID(sessionUsernameMap.get(session));
		sessionUsernameMap.remove(session);
		usernameSessionMap.remove(u.getUsername());

		String message = u.getUsername() + " disconnected";
		broadcast(message);
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		// Do error handling here
		logger.info("Entered into Error");
	}

	// sends message to specific client
	private void sendMessageToPArticularUser(String username, String message) {
		try {
			usernameSessionMap.get(username).getBasicRemote().sendText(message);
		} catch (IOException e) {
			logger.info("Exception: " + e.getMessage().toString());
			e.printStackTrace();
		}
	}

	// sends a message to all clients connected to the server

	// modify this so that we can broadcast if a user's time is 0, meaning that they
	// are able to drive
	private static void broadcast(String message) throws IOException {
		sessionUsernameMap.forEach((session, username) -> {
			synchronized (session) {
				try {
					session.getBasicRemote().sendText(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
