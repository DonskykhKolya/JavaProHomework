package ua.kiev.prog;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name="QuestionnaireServlet", urlPatterns="/question" )
public class QuestionnaireServlet extends javax.servlet.http.HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        int age = Integer.parseInt(req.getParameter("age"));
        String q1 = req.getParameter("q1");
        String q2 = req.getParameter("q2");
        Questionnaire.save(firstname, lastname, age, q1, q2);

        String statistics = Questionnaire.getStatistics();
        HttpSession session = req.getSession(true);
        session.setAttribute("statistics", statistics);

        resp.sendRedirect("statistics.jsp");
    }
}
