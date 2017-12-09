package ua.kiev.prog.dao;

import ua.kiev.prog.entity.Person;

import java.util.List;


public interface PersonDAO {

    void add(Person newPerson);
    List<Person> getAll();
    void delete(int id);
}
