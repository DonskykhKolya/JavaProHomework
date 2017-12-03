package ua.kiev.prog.storage;

import ua.kiev.prog.model.Status;
import ua.kiev.prog.model.User;

import java.util.*;


public class UserList {

    private static UserList instance;

    private List<User> users = new ArrayList<>();

    private UserList() {
        initUsers();
    }

    private void initUsers() {
        users.add(new User("admin", "admin"));
        users.add(new User("test", "test"));
    }

    public static synchronized UserList getInstance() {
        if(instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    public synchronized String add(String login, String password) {

        String sessionId = "";

        Optional<User> user = users.stream().findAny().filter(u -> u.getLogin().equals(login));
        if(!user.isPresent()) {
            sessionId = UUID.randomUUID().toString();
            User newUser = new User(login, password);
            newUser.setStatus(Status.ON);
            newUser.setSessionId(sessionId);
            users.add(newUser);
        }

        return sessionId;
    }

    public synchronized String check(String login, String password) {

        String sessionId = "";

        Optional<User> user = users.stream().findAny().filter(u -> u.getLogin().equals(login) && u.getPassword().equals(password));
        if(user.isPresent()) {
            sessionId = UUID.randomUUID().toString();
            user.get().setSessionId(sessionId);
            user.get().setStatus(Status.ON);
        }

        return sessionId;
    }

    public synchronized boolean isValid(String sessionId) {
        Optional<User> user = users.stream().findAny().filter(u -> u.getSessionId().equals(sessionId));
        return user.isPresent();
    }
}
