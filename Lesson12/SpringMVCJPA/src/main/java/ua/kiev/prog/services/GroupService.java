package ua.kiev.prog.services;

import ua.kiev.prog.model.Group;

import java.util.List;

public interface GroupService {

    List<Group> listGroups();
    void add(Group group);
    void delete(long id);
    Group findGroup(long id);
}
