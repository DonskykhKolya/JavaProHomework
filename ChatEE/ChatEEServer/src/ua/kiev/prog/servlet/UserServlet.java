package ua.kiev.prog.servlet;

import ua.kiev.prog.service.SecurityService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name="UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Cookie[] cookies = req.getCookies();
        if(!SecurityService.validate(cookies)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String status = req.getParameter("status");
    }
}
