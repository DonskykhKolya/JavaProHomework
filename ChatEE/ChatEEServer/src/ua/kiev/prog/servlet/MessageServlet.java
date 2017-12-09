package ua.kiev.prog.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.model.Message;
import ua.kiev.prog.service.SecurityService;
import ua.kiev.prog.storage.MessageList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


@WebServlet(name="MessageServlet", urlPatterns = "/message")
public class MessageServlet extends HttpServlet {

    private SecurityService securityService = new SecurityService();
    private MessageList msgList = MessageList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Cookie[] cookies = req.getCookies();
        if(!securityService.validate(cookies)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Cookie[] cookies = req.getCookies();
        if(!securityService.validate(cookies)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        byte[] buf = requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);
        Gson gson = new GsonBuilder().create();
        Message msg = gson.fromJson(bufStr, Message.class);

        if (msg != null)
            msgList.add(msg);
        else
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    private byte[] requestBodyToArray(HttpServletRequest req) throws IOException {

        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}
