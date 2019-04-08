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
import org.springframework.stereotype.Component;

@ServerEndpoint("/websocket/{username}")
@Component
public class WebSocketServer {

	// Store all socket session and their corresponding username.
	private static Map<Session, String> sessionUsernameMap = new HashMap<>();
	private static Map<String, Session> usernameSessionMap = new HashMap<>();
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

	private static ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();

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

		// String temp = message.substring(0, 4); String x = message.substring(5,
		// message.length()); //save the username in the message in case we're adding

		if (message.startsWith("@")) // Direct message to a user using the format "@username <message>"
		{
			String destUsername = message.split(" ")[0].substring(1); // don't do this in your code!
			sendMessageToPArticularUser(destUsername, "[DM] " + username + ": " + message);
			sendMessageToPArticularUser(username, "[DM] " + username + ": " + message);
		}

		else if (message.equals("!help")) {
			broadcast("List of commands: \n" + "---------------\n" + "GROUP COMMANDS\n" + "---------------\n"
					+ "Create group: !group\n" + "List members: !get_members\n" + "Leave group: !leave\n"
					+ "Add member: !add [username]\n");

		}

		else if (message.equals("!group")) {
			broadcast(username + " has started a group");
			ArrayList<String> newGroup = new ArrayList<String>();
			newGroup.add(username);
			groups.add(newGroup);

		} else if (message.equals("!get_members")) { // hasn't been tested
			boolean did_leave = false;
			int s = 0;
			for (; s < groups.size(); s++) {
				if (groups.get(s).contains(username)) {
					String broadcast_message = "";
					for (int i = 0; i < groups.get(s).size(); i++)
						broadcast_message += groups.get(s).get(i) + " ";
					broadcast(broadcast_message);
					s = groups.size();
					did_leave=true;
				}
			}
			if(!did_leave)
				broadcast("No group");
		} 
		else if (message.equals("!leave")) {
			boolean did_leave = false;
			for (int i = 0; i < groups.size(); i++) {
				if (groups.get(i).contains(username)) {
					groups.get(i).remove(username);
					broadcast(username + " has left the group");
					did_leave =true;
				}
			}
			if(!did_leave)
				broadcast("No group to leave");
			
		}

		else if (message.length() > 5 && message.substring(0, 4).equals("!add")) { // add to the group
				for (int i = 0; i < groups.size(); i++) {
					if (groups.get(i).contains(username)) {
						groups.get(i).add(message.substring(5, message.length()));
					}
				}
				broadcast(username + " has added " + message.substring(5, message.length()));

			}
		else {
			broadcast(username + ": " + message);
		}

	}

	@OnClose
	public void onClose(Session session) throws IOException {
		String username = sessionUsernameMap.get(session);
		logger.info("Entered into Close");

		sessionUsernameMap.remove(session);
		usernameSessionMap.remove(username);

		String message = username + " disconnected";
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
