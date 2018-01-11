package ua.kiev.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.dto.GroupDTO;
import ua.kiev.prog.model.Contact;
import ua.kiev.prog.model.Group;
import ua.kiev.prog.repository.ContactRepository;
import ua.kiev.prog.repository.GroupRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Transactional
    public void addContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Transactional
    public void deleteContacts(long[] idList) {
        for (long id : idList)
            contactRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public List<Contact> findAll(Pageable pageable) {
        return contactRepository.findAll(pageable).getContent();
    }

    @Transactional(readOnly = true)
    public List<Contact> findByGroup(Group group, Pageable pageable) {
        return contactRepository.findByGroup(group, pageable);
    }

    @Transactional(readOnly = true)
    public long countByGroup(Group group) {
        return contactRepository.countByGroup(group);
    }

    @Transactional(readOnly = true)
    public List<Contact> findByPattern(String pattern, Pageable pageable) {
        return contactRepository.findByPattern(pattern, pageable);
    }

    @Transactional(readOnly = true)
    public long count() {
        return contactRepository.count();
    }

    @Transactional
    public void addGroup(Group group) {
        groupRepository.save(group);
    }

    @Transactional(readOnly = true)
    public List<Group> findGroups() {
        return groupRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Group findGroup(long id) {
        return groupRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<GroupDTO> getGroupsInfo() {
        List<GroupDTO> groupInfo = new ArrayList<>();
        List<Group> groups = groupRepository.findAll();
        for (Group group : groups) {
            GroupDTO dto = new GroupDTO(group.getId(), group.getName(), group.getContacts().size());
            groupInfo.add(dto);
        }
        return groupInfo;
    }

    @Transactional
    public void deleteWithContacts(long[] idList) {
        for (long id : idList)
            groupRepository.delete(id);
    }

    @Transactional
    public void deleteWithOutContacts(long[] idList) {
        for (long id : idList) {
            Group group = groupRepository.findOne(id);
            group.getContacts().forEach(c -> c.setGroup(null));
            group.getContacts().clear();
            groupRepository.delete(id);
        }
    }
}
