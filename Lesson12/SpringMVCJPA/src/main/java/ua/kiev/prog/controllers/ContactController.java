package ua.kiev.prog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import ua.kiev.prog.model.Contact;
import ua.kiev.prog.model.Group;
import ua.kiev.prog.services.ContactService;
import ua.kiev.prog.services.GroupService;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static ua.kiev.prog.controllers.GroupController.DEFAULT_GROUP_ID;

@Controller
public class ContactController {
    private static final int ITEMS_PER_PAGE = 6;

    @Autowired
    private ContactService contactService;
    @Autowired
    private GroupService groupService;

    @RequestMapping("/")
    public String index(Model model, @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;

        long totalCount = contactService.count();
        int start = page * ITEMS_PER_PAGE;
        long pageCount = (totalCount / ITEMS_PER_PAGE) +
                ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);

        model.addAttribute("groups", groupService.listGroups());
        model.addAttribute("contacts", contactService.listContacts(null, start, ITEMS_PER_PAGE));
        model.addAttribute("pages", pageCount);

        return "index";
    }

    @RequestMapping("/contact_add_page")
    public String contactAddPage(Model model) {
        model.addAttribute("groups", groupService.listGroups());
        return "contact_add_page";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String pattern, Model model) {
        model.addAttribute("groups", groupService.listGroups());
        model.addAttribute("contacts", contactService.searchContacts(pattern));
        return "index";
    }

    @RequestMapping(value = "/contact/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> delete(@RequestParam(value = "toDelete[]", required = false) long[] toDelete) {
        if (toDelete != null && toDelete.length > 0)
            contactService.delete(toDelete);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/contact/add", method = RequestMethod.POST)
    public String contactAdd(@RequestParam(value = "group") long groupId,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String phone,
                             @RequestParam String email) {
        Group group = (groupId != DEFAULT_GROUP_ID) ? groupService.findGroup(groupId) : null;

        Contact contact = new Contact(group, name, surname, phone, email);
        contactService.add(contact);

        return "redirect:/";
    }

    @RequestMapping("/export/{id}")
    public void exportToCsv(@PathVariable(value = "id") long groupId, HttpServletResponse response) throws IOException {

        Group group = (groupId != DEFAULT_GROUP_ID) ? groupService.findGroup(groupId) : null;
        List<Contact> contacts = contactService.listContacts(group);

        String csvFileName = "contacts.csv";
        response.setContentType("text/csv");

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader(headerKey, headerValue);

        // uses the Super CSV API to generate CSV data from the model data
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        String[] header = {"Group", "Name", "Surname", "Phone", "Email"};
        csvWriter.writeHeader(header);
        contacts.forEach(c -> {
            try {
                csvWriter.write(c, header);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        csvWriter.close();
    }
}
