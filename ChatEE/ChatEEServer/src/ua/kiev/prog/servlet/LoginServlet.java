package ua.kiev.prog.servlet;

import ua.kiev.prog.model.UserList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name="LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private UserList userList = UserList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        String sessionId = userList.validate(login, password);
        if(sessionId.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        else {
            Cookie cookie = new Cookie("session_id", sessionId);
            cookie.setMaxAge(3600);
            resp.addCookie(cookie);
        }
    }
}
