package ua.kiev.prog.servlet;

import ua.kiev.prog.model.Status;
import ua.kiev.prog.service.SecurityService;
import ua.kiev.prog.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name="UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    private SecurityService securityService = new SecurityService();
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Cookie[] cookies = req.getCookies();
        String sessionId = securityService.validateAndGet(cookies);
        if(sessionId.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String s = req.getParameter("status");
        Status status;
        switch(s) {
            case "ON":
                status = Status.ON;
                break;
            case "OFF":
                status = Status.OFF;
                break;
            default:
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
        }

        if(!userService.changeStatus(sessionId, status)) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
