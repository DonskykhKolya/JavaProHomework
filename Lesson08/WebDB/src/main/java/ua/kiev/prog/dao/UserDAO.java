package ua.kiev.prog.dao;

import ua.kiev.prog.entity.User;

import java.util.List;


public interface UserDAO {

    void add(User newUser);
    List<User> getAll();
    void delete(int id);
}
