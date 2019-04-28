package org.springframework.samples.drink_safe.WebSocket;

import java.io.IOException;
import java.security.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

/**
 * The websockets we've used in our application
 * 
 * @author Jeremy and Nick
 *
 */
@ServerEndpoint(value = "/websocket/{username}", configurator = CustomConfigurator.class)
@Component
public class WebSocketServer {

	// Store all socket session and their corresponding username.
	private static Map<Session, String> sessionUsernameMap = new HashMap<>();
	private static Map<String, Session> usernameSessionMap = new HashMap<>();
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
	private static ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();

	@Autowired
	UserRepository userRepo;

	@Autowired
	UserController r;

	/**
	 * When a user joins the chat
	 * 
	 * @param session  - the client
	 * @param username
	 * @throws IOException
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) throws IOException {
		logger.info("Entered into Open");

		sessionUsernameMap.put(session, username);
		usernameSessionMap.put(username, session);

		User u = userRepo.findByUsername(username);
		String message = "User:" + u.getUsername() + " has Joined the Chat";
		broadcast(message);

	}

	/**
	 * Messaging system between users
	 * 
	 * @param session
	 * @param message
	 * @throws IOException
	 */
	@OnMessage
	public void onMessage(Session session, String message) throws IOException {

		// Handle new messages
		logger.info("Entered into Message: Got Message:" + message);

		User u = userRepo.findByUsername(sessionUsernameMap.get(session));

		if (message.startsWith("@")) // Direct message to a user using the format "@username <message>"
		{
			String destUsername = message.split(" ")[0].substring(1); // don't do this in your code!
			sendMessageToPArticularUser(destUsername, "[DM] " + u.getUsername() + ": " + message);
			sendMessageToPArticularUser(u.getUsername(), "[DM] " + u.getUsername() + ": " + message);
		}

		// lists all the available commands to the user
		else if (message.equals("!help")) {
			broadcast("List of commands: \n" + "---------------\n" + "GROUP COMMANDS\n" + "---------------\n"
					+ "Create group: !group\n" + "List members: !get_members\n" + "Leave group: !leave\n"
					+ "Add member: !add [username]\n" + "Check if members are sober: !check\n");

		} else if (message.equals("!time")) { // check what time the user is able to drive
			// u.getUser_time().getTime_finish()
			if (u.getUser_time() != null) {

				Date date = new Date(u.getUser_time().getTime_finish()-System.currentTimeMillis());
				Format format = new SimpleDateFormat("HH:mm:ss");

				broadcast(u.getUsername() + " is sober at " + format.format(date));
			

			} else {
				broadcast(u.getUsername() + " is sober now");
			}
		}

		// create a new group
		else if (message.equals("!group")) {
			broadcast(u.getUsername() + " has started a group");
			ArrayList<String> newGroup = new ArrayList<String>();
			newGroup.add(u.getUsername());
			groups.add(newGroup);

		}

		// list all the members
		else if (message.equals("!get_members")) {
			boolean did_leave = false;
			int s = 0;
			for (; s < groups.size(); s++) {
				if (groups.get(s).contains(u.getUsername())) {
					String broadcast_message = "";
					for (int i = 0; i < groups.get(s).size(); i++)
						broadcast_message += groups.get(s).get(i) + " ";
					broadcast(broadcast_message);
					s = groups.size();
					did_leave = true;
				}
			}
			if (!did_leave)
				broadcast("No group");
		}

		// leave a group if someone's in one
		else if (message.equals("!leave")) {
			boolean did_leave = false;
			for (int i = 0; i < groups.size(); i++) {
				if (groups.get(i).contains(u.getUsername())) {
					groups.get(i).remove(u.getUsername());
					broadcast(u.getUsername() + " has left the group");
					did_leave = true;
				}
			}
			if (!did_leave)
				broadcast("No group to leave");
		}

		// add someone to the group
		else if (message.length() > 5 && message.substring(0, 4).equals("!add")) { // add to the group
			for (int i = 0; i < groups.size(); i++) {
				if (groups.get(i).contains(u.getUsername())) {
					groups.get(i).add(message.substring(5, message.length()));
				}
			}
			broadcast(u.getUsername() + " has added " + message.substring(5, message.length()));

		}

		// check which users are able to drive or not
		else if (message.equals("!check")) {

			List<User> m = r.findByGuest_Status(0); // first find all the sober users

			if (m.size() > 0) { // if there are users able to drive
				broadcast("Current users able to drive:");
				for (int p = 0; p < m.size(); p++) {
					broadcast(m.get(p).getUsername());
				}
			}

			else { // otherwise there shouldn't be anyone available
				broadcast("No one is able to drive.");
			}

		}

		// otherwise it's just a regular message, and it will be sent to everyone
		else {
			broadcast(u.getUsername() + ": " + message);

		}

	}

	/**
	 * When a user disconnects from the chat, it tells everyone that they left
	 * 
	 * @param session
	 * @throws IOException
	 */
	@OnClose
	public void onClose(Session session) throws IOException {
		String username = sessionUsernameMap.get(session);
		logger.info("Entered into Close");

		sessionUsernameMap.remove(session);
		usernameSessionMap.remove(username);

		String message = username + " disconnected";
		broadcast(message);
	}

	/**
	 * Error handling
	 * 
	 * @param session
	 * @param throwable
	 */
	@OnError
	public void onError(Session session, Throwable throwable) {

		logger.info("Entered into Error");
		// throwable.printStackTrace();
	}

	/**
	 * Sends a message to a particular user
	 * 
	 * @param username - the user to send a message to
	 * @param message  - the message to be sent
	 */
	private void sendMessageToPArticularUser(String username, String message) {
		try {
			usernameSessionMap.get(username).getBasicRemote().sendText(message);
		} catch (IOException e) {
			logger.info("Exception: " + e.getMessage().toString());
			e.printStackTrace();
		}
	}

	/**
	 * This handles all the messaging across clients
	 * 
	 * @param message
	 * @throws IOException
	 */
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
