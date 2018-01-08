package ua.kiev.prog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kiev.prog.model.Contact;
import ua.kiev.prog.model.Group;
import ua.kiev.prog.services.ContactService;
import ua.kiev.prog.services.GroupService;

@Controller
public class GroupController {
    static final int DEFAULT_GROUP_ID = -1;

    @Autowired
    private ContactService contactService;
    @Autowired
    private GroupService groupService;

    @RequestMapping("/group_add_page")
    public String groupAddPage() {
        return "group_add_page";
    }

    @RequestMapping(value = "/group/add", method = RequestMethod.POST)
    public String groupAdd(@RequestParam String group_name,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String phone,
                           @RequestParam String email) {
        Group group = new Group(group_name);
        groupService.add(group);

        Contact contact = new Contact(group, name, surname, phone, email);
        contactService.add(contact);

        return "redirect:/";
    }

    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    public String listGroup(@PathVariable(value = "id") long groupId, Model model) {
        Group group = (groupId != DEFAULT_GROUP_ID) ? groupService.findGroup(groupId) : null;

        model.addAttribute("group_id", groupId);
        model.addAttribute("groups", groupService.listGroups());
        model.addAttribute("contacts", contactService.listContacts(group));

        return "index";
    }

    @RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteGroup(@PathVariable(value = "id") long groupId, Model model) {
        if(groupId != DEFAULT_GROUP_ID) {
            groupService.delete(groupId);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
