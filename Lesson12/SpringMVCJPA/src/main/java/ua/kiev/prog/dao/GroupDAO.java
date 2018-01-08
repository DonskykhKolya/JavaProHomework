package ua.kiev.prog.dao;

import ua.kiev.prog.model.Group;

import java.util.List;

public interface GroupDAO {
    void add(Group group);
    void delete(long id);
    Group findOne(long id);
    List<Group> list();
}
