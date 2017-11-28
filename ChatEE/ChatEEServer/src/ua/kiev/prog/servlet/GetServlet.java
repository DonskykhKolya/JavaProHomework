package ua.kiev.prog.servlet;

import ua.kiev.prog.model.MessageList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


@WebServlet(name="GetServlet", urlPatterns = "/get")
public class GetServlet extends HttpServlet {

    private MessageList msgList = MessageList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("session_id")){
                    String value = cookie.getValue();
                    break;
                }
            }
        }

        String fromStr = req.getParameter("from");
        int from = 0;
        try {
            from = Integer.parseInt(fromStr);
        } catch (Exception ex) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String json = msgList.toJSON(from);
        if (json != null) {
            OutputStream os = resp.getOutputStream();
            os.write(json.getBytes(StandardCharsets.UTF_8));
        }
    }
}
