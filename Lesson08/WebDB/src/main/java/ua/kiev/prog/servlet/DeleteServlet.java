package ua.kiev.prog.servlet;

import ua.kiev.prog.dao.PersonDAO;
import ua.kiev.prog.dao.PersonDAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Integer> idList = getIdList(req.getParameter("ids"));
        if(idList == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        PersonDAO dao = new PersonDAOImpl(em);
        try {
            idList.forEach(dao::delete);
        } finally {
            em.close();
        }

        resp.sendRedirect("list");
    }

    private List<Integer> getIdList(String idStr) {

        List<Integer> ids = new ArrayList<>();
        try {
            String[] idArr = idStr.split(",");
            for (String s : idArr) {
                int id = Integer.parseInt(s);
                ids.add(id);
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return ids;
    }
}
