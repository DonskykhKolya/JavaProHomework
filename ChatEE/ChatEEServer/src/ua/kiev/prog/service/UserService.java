package ua.kiev.prog.service;

import ua.kiev.prog.model.Status;
import ua.kiev.prog.storage.UserList;


public class UserService {

    private UserList userList = UserList.getInstance();

    public String addUser(String login, String password) {
        return userList.add(login, password);
    }

    public boolean changeStatus(String sessionId, Status newStatus) {
        return userList.changeStatus(sessionId, newStatus);
    }
}
