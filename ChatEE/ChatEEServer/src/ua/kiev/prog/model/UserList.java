package ua.kiev.prog.model;

import java.util.*;


public class UserList {

    private static UserList instance;

    private List<User> users = new ArrayList<>();

    private UserList() {
        initUsers();
    }

    private void initUsers() {
        User user1 = new User("admin", "admin");
        User user2 = new User("test", "test");
        user1.addContact(user2);
        user2.addContact(user1);
        users.add(user1);
        users.add(user2);
    }

    public static synchronized UserList getInstance() {
        if(instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    public synchronized void add(String login, String password) {
        Optional<User> user = users.stream().findAny().filter(u -> u.getLogin().equals(login));
        if(!user.isPresent()) {
            users.add(new User(login, password));
        }
    }

    public synchronized String validate(String login, String password) {
        String sessionId = "";
        Optional<User> user = users.stream().findAny().filter(u -> u.getLogin().equals(login) && u.getPassword().equals(password));
        if(user.isPresent()) {
            sessionId = UUID.randomUUID().toString();
            user.get().setSessionId(sessionId);
            user.get().setStatus(Status.ON);
        }
        return sessionId;
    }
}
