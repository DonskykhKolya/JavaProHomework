package ua.kiev.prog.services;

import ua.kiev.prog.model.Contact;
import ua.kiev.prog.model.Group;

import java.util.List;

public interface ContactService {
    void add(Contact contact);
    void delete(long[] ids);
    List<Contact> listContacts(Group group, int start, int count);
    List<Contact> listContacts(Group group);
    long count();
    List<Contact> searchContacts(String pattern);
}
