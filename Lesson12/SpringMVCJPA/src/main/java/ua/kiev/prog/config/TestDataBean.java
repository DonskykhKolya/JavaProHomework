package ua.kiev.prog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.kiev.prog.model.Contact;
import ua.kiev.prog.model.Group;
import ua.kiev.prog.services.ContactService;
import ua.kiev.prog.services.GroupService;

import javax.annotation.PostConstruct;

@Component
public class TestDataBean {
    @Autowired
    private ContactService contactService;
    @Autowired
    private GroupService groupService;

    @PostConstruct
    public void fillData() {
        Group group = new Group("Test");
        Contact contact;

        groupService.add(group);

        for (int i = 0; i < 25; i++) {
            contact = new Contact(null, "Name" + i, "Surname" + i, "1234567" + i, "user" + i + "@test.com");
            contactService.add(contact);
        }
        for (int i = 0; i < 12; i++) {
            contact = new Contact(group, "Other" + i, "OtherSurname" + i, "7654321" + i, "user" + i + "@other.com");
            contactService.add(contact);
        }
    }
}
