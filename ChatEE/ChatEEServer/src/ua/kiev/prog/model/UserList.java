package ua.kiev.prog.model;

import java.util.*;


public class UserList {

    private static UserList instance;

    private List<User> users = new ArrayList<>();

    private UserList() {
        users.add(new User("admin", "admin"));
        users.add(new User("test", "test"));
    }

    public static synchronized UserList getInstance() {
        if(instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    public synchronized void add(String login, String password) {
        if(!userExists(login, password)) {
            users.add(new User(login, password));
        }
    }

    private synchronized boolean userExists(String login, String password) {
        Optional<User> user = users.stream().filter(u -> u.getLogin().equals(login) && u.getPassword().equals(password)).findAny();
        return user.isPresent();
    }

    public synchronized String checkUser(String login, String password) {
        String sessionId = "";
        Optional<User> user = users.stream().filter(u -> u.getLogin().equals(login) && u.getPassword().equals(password)).findAny();
        if(user.isPresent()) {
            sessionId = UUID.randomUUID().toString();
            user.get().setSessionId(sessionId);
        }
        return sessionId;
    }
}
