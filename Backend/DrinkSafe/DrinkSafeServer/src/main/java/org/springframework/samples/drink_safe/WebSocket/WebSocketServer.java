package org.springframework.samples.drink_safe.WebSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.stereotype.Component;

@ServerEndpoint("/WebSocket/{username}")
@Component
public class WebSocketServer {

	// Store all socket session and their corresponding username.
	private static Map<Session, String> sessionUsernameMap = new HashMap<>();
	private static Map<String, Session> usernameSessionMap = new HashMap<>();
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

	private static ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();

	@Autowired
	UserRepository userRepo;

	UserController x = new UserController();

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
		String username = sessionUsernameMap.get(session);
		//User r = x.findUserbyID(username);


		if (message.startsWith("@")) // Direct message to a user using the format "@username <message>"
		{
			String destUsername = message.split(" ")[0].substring(1); // don't do this in your code!
			sendMessageToPArticularUser(destUsername, "[DM] " + username + ": " + message);
			sendMessageToPArticularUser(username, "[DM] " + username + ": " + message);
		}

		if (message.equals("!help")) {
			broadcast("List of commands: \n" + "---------------\n" + "GROUP COMMANDS\n" + "---------------\n"
					+ "Create group: !group\n" + "List members: !get_members\n" + "Leave group: !leave\n"
					+ "Add member: !add [username]\n");

		}
		
		if(message.equals("!group")) {
			broadcast(username + " has started a group");
			ArrayList<String> newGroup = new ArrayList<String>();
			newGroup.add(username);
			groups.add(newGroup);

		}
		
		if(message.equals("!get_members")) { //hasn't been tested
			for(int i = 0; i < groups.size(); i++) {
				broadcast("In group " + i + ": ");
				for(int k = 0; k < groups.get(i).size(); k++) {
					broadcast(groups.get(i).get(i)); //list member			
				}				
			}
		}
		
		
		if(message.equals("!leave")) {
			for(int i = 0; i < groups.size(); i++) {
				if(groups.get(i).contains(username)) {
					groups.get(i).remove(username); //remove from the arraylist, might be wrong (?)
				}
			}
		}
		
		if(message.substring(0, 4).equals("!add ")) { //add to the group
			for(int i = 0; i < groups.size(); i++) {
				if(groups.get(i).contains(username)) {
					groups.get(i).add(username.substring(5, message.length()));
				}
			}
		}
		
		
		

		else {// Message to whole chat

			broadcast(username + ": " + message);
		}


		// if they aren't in a group, they shouldn't be able to run these commands
		/*
		 * if (!u.toModifyBuddies().isEmpty() && !u.toModifyInvitee().isEmpty()) {
		 * 
		 * if (message.equals("!get_members")) { broadcast("List of members:");
		 * System.out.println(x.getGroup(u.getUsername())); }
		 * 
		 * if (message.substring(0, 5).equals("!add ")) {
		 * 
		 * if (u.toModifyBuddies().isEmpty()) { User u2 =
		 * userRepo.findByUsername(message.substring(6, message.length() - 1));
		 * 
		 * x.addGroup(u.getUsername(), u2.getUsername());
		 * 
		 * }
		 * 
		 * if (message.substring(6, message.length() - 1).equals(u.getUsername())) {
		 * broadcast("You're already in the group!"); } else {
		 * broadcast("User does not exist"); } }
		 * 
		 * if (message.equals("!leave")) { if (!u.toModifyBuddies().isEmpty()) {
		 * 
		 * } broadcast(u.getUsername() + " has left the group."); } else {
		 * broadcast("You are not currently in a group."); }
		 */
		

	}

	@OnClose
	public void onClose(Session session) throws IOException {
		logger.info("Entered into Close");

		User u = userRepo.findByUsername(sessionUsernameMap.get(session));
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
