package ua.kiev.prog.servlet;

import ua.kiev.prog.entity.Address;
import ua.kiev.prog.entity.User;
import ua.kiev.prog.service.DataService;
import ua.kiev.prog.service.DataServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = getUser(req);
        if (user == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Address mainAddress = getAddress(req, 1);
        Address secondAddress = getAddress(req, 2);
        user.addAddress(mainAddress);
        user.addAddress(secondAddress);

        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        DataService service = new DataServiceImpl(em);
        try {
            service.addUser(user);
        } finally {
            em.close();
        }

        resp.sendRedirect("list");
    }

    private User getUser(HttpServletRequest req) {

        String name = req.getParameter("name");
        String age = req.getParameter("age");
        int iAge;

        try {
            iAge = Integer.parseInt(age);
        } catch (Exception ex) {
            return null;
        }

        return new User(name, iAge);
    }

    private Address getAddress(HttpServletRequest req, int index) {

        String country = req.getParameter("country" + index);
        String city = req.getParameter("city" + index);
        String street = req.getParameter("street" + index);
        String house = req.getParameter("house" + index);

        return new Address(country, city, street, house);
    }
}
