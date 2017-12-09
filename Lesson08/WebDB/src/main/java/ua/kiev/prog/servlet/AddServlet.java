package ua.kiev.prog.servlet;


import ua.kiev.prog.dao.PersonDAO;
import ua.kiev.prog.dao.PersonDAOImpl;
import ua.kiev.prog.entity.Person;

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

        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        PersonDAO dao = new PersonDAOImpl(em);
        try {
            dao.add(new Person(name, iAge));
        } finally {
            em.close();
        }

        resp.sendRedirect("list");
    }
}
