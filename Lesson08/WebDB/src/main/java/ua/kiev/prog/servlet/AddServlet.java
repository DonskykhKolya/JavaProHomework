package ua.kiev.prog.servlet;


import ua.kiev.prog.dao.UserDAO;
import ua.kiev.prog.dao.UserDAOImpl;
import ua.kiev.prog.entity.Address;
import ua.kiev.prog.entity.User;

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

        String name = req.getParameter("name");
        String age = req.getParameter("age");
        int iAge = 0;

        try {
            iAge = Integer.parseInt(age);
        } catch (Exception ex) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        String house = req.getParameter("house");

        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        UserDAO dao = new UserDAOImpl(em);
        try {
            User user = new User(name, iAge);
            Address address = new Address(country, city, street, house);
            user.setAddress(address);
            address.setUser(user);
            dao.add(user);
        } finally {
            em.close();
        }

        resp.sendRedirect("list");
    }
}
