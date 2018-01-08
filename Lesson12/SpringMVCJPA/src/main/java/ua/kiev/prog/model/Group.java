package ua.kiev.prog.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Groups")
public class Group {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Contact> contacts = new ArrayList<>();

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        contacts.forEach(c -> c.setGroup(this));
    }

    public void addContact(Contact contact) {
        if (!contacts.contains(contact)) {
            contacts.add(contact);
            contact.setGroup(this);
        }
    }

    public void deleteContact(Contact contact) {
        if (contacts.contains(contact)) {
            contacts.remove(contact);
            contact.setGroup(null);
        }
    }
}
