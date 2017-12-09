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


public class DeleteServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String ids = req.getParameter("ids");

        /*EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        PersonDAO dao = new PersonDAOImpl(em);
        try {
            dao.delete(id);
        } finally {
            em.close();
        }*/
    }
}
