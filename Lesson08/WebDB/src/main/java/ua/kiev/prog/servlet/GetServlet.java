package ua.kiev.prog.servlet;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.util.List;


public class GetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        PersonDAO dao = new PersonDAOImpl(em);
        try {
            List<Person> personList = dao.getAll();

            Gson gson = new GsonBuilder().create();
            String json = gson.toJson(personList);

            resp.setContentType("application/json");
            resp.getWriter().print(json);
        } finally {
            em.close();
        }
    }
}

