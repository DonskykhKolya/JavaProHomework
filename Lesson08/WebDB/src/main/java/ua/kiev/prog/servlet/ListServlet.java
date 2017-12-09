package ua.kiev.prog.servlet;

import ua.kiev.prog.dao.PersonDAO;
import ua.kiev.prog.dao.PersonDAOImpl;
import ua.kiev.prog.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        PersonDAO dao = new PersonDAOImpl(em);
        try {
            List<Person> personList = dao.getAll();
            req.setAttribute("personList", personList);
        } finally {
            em.close();
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/result.jsp");
        dispatcher.forward(req, resp);
    }
}
