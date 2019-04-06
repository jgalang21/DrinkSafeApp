package org.springframework.samples.drink_safe.WebSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

@ServerEndpoint("/WebSocket/{username}")
@Component
public class WebSocketServer {

	// Store all socket session and their corresponding username.
	private static Map<Session, String> sessionUsernameMap = new HashMap<>();
	private static Map<String, Session> usernameSessionMap = new HashMap<>();
	private static ArrayList<String> group1 = new ArrayList<String>();

	private final org.slf4j.Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

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
		String username = sessionUsernameMap.get(session);

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

		if (message.equals("!group")) {
			broadcast(username + " has started a new group.");
			group1.add(username);
		}

		if (message.equals("!get_members")) {
			broadcast("List of members:");
			for (int i = 0; i < group1.size(); i++) {
				broadcast(group1.get(i));
			}
		}

		if (message.equals("!leave")) {
			if (group1.contains(username)) {
				int temp = 0;
				int k;
				for (k = 0; k < group1.size(); k++) {
					if (group1.get(k).equals(username)) {
						temp = k;
						break;
					}
				}
				group1.remove(k);
				broadcast(username + " has left the group.");
			} else {
				broadcast("You are not currently in a group.");
			}
		}

		// adding a member
		if (message.substring(0, 3).equals("!add")) {

			if (!usernameSessionMap.containsKey(message.substring(5, message.length() - 1))
					&& !group1.contains(message.substring(5, message.length() - 1))) {
				group1.add(message.substring(5, message.length() - 1));
			}
			else {
				broadcast("User does not exist");
			}

		}

		else // Message to whole chat
		{
			broadcast(username + ": " + message);
		}
	}

	@OnClose
	public void onClose(Session session) throws IOException {
		logger.info("Entered into Close");

		String username = sessionUsernameMap.get(session);
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
