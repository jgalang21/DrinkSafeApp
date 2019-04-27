package org.springframework.samples.drink_safe.WebSocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.drink_safe.user.User;
import org.springframework.samples.drink_safe.user.UserRepository;

//@ServerEndpoint(value = "/userWork/{userName}", configurator = CustomConfigurator.class)
public class endpoint{
	/*
	@Autowired
	UserRepository userRepo;
	
	private static Map<Session, String> sessionUsernameMap = new HashMap<>();
	private static Map<String, Session> usernameSessionMap = new HashMap<>();
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
	
	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) throws IOException {
		logger.info("Entered into Open");

		sessionUsernameMap.put(session, username);
		usernameSessionMap.put(username, session);
		
		User u = userRepo.findByUsername(username);
		String message = "User:" + u.getUsername() + " has Joined the Chat";
		broadcast(message);

	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		throwable.printStackTrace();
		logger.info("Entered into Error");
	}
	
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
	*/
}	