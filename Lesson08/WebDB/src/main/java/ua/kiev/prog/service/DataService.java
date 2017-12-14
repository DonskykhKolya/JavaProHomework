package ua.kiev.prog.service;

import ua.kiev.prog.entity.User;

import java.util.List;


public interface DataService {

    void addUser(User user);
    List<User> getAllUsers();
    void deleteUser(int id);
}
