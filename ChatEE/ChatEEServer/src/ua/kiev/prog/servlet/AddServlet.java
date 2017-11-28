package ua.kiev.prog.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.model.Message;
import ua.kiev.prog.model.MessageList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


@WebServlet(name="AddServlet", urlPatterns = "/add")
public class AddServlet extends HttpServlet {

    private MessageList msgList = MessageList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

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
