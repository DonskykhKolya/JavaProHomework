package ua.kiev.prog.service;

import ua.kiev.prog.model.Status;
import ua.kiev.prog.storage.UserList;


public class UserService {

    private static UserList userList = UserList.getInstance();

    public static String addUser(String login, String password) {
        return userList.add(login, password);
    }

    public static void changeStatus(Status status) {

    }
}
