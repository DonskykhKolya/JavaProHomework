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
