package ua.kiev.prog.servlet;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.dao.UserDAO;
import ua.kiev.prog.dao.UserDAOImpl;
import ua.kiev.prog.entity.User;

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

        UserDAO dao = new UserDAOImpl(em);
        try {
            List<User> userList = dao.getAll();

            Gson gson = new GsonBuilder().create();
            String json = gson.toJson(userList);

            resp.setContentType("application/json");
            resp.getWriter().print(json);
        } finally {
            em.close();
        }
    }
}

