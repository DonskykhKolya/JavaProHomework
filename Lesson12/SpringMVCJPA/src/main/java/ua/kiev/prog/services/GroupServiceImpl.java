package ua.kiev.prog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.dao.GroupDAO;
import ua.kiev.prog.model.Group;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupDAO groupDAO;

    @Override
    @Transactional(readOnly=true)
    public List<Group> listGroups() {
        return groupDAO.list();
    }

    @Override
    @Transactional
    public void add(Group group) {
        groupDAO.add(group);
    }

    @Override
    @Transactional
    public void delete(long id) {
        groupDAO.delete(id);
    }

    @Override
    @Transactional(readOnly=true)
    public Group findGroup(long id) {
        return groupDAO.findOne(id);
    }
}
