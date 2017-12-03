package ua.kiev.prog.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.model.Message;
import ua.kiev.prog.model.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class HttpClient {

    private static final String BASE_URL = "http://127.0.0.1:8080";

    private Gson gson;
    private CookieManager cookieManager;


    public HttpClient() {

        gson = new GsonBuilder().create();

        cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
    }


    public boolean login(String login, String password) {

        try {
            String url = BASE_URL + "/login?login=" + login + "&password=" + password;
            HttpURLConnection conn = initConnection(url, "POST", false);
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(gson.toJson(new User(login, password)).getBytes());
            }

            if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return false;
            }

            saveCookies(conn);

        } catch(IOException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    private HttpURLConnection initConnection(String urlStr, String method, boolean addCookies) throws IOException {

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestProperty("Content-Type","application/json");
        conn.setRequestMethod(method);

        if(addCookies) {
            conn.setRequestProperty("Cookie", getCookiesString());
        }

        return conn;
    }

    private String getCookiesString() {
        StringBuilder sb = new StringBuilder();
        for(HttpCookie cookie : cookieManager.getCookieStore().getCookies()) {
            sb.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
        }
        return sb.toString();
    }

    private void saveCookies(HttpURLConnection conn) {

        String cookiesHeader = conn.getHeaderField("Set-Cookie");
        if(cookiesHeader != null) {
            List<HttpCookie> cookies = HttpCookie.parse(cookiesHeader);
            cookies.forEach(cookie -> cookieManager.getCookieStore().add(null, cookie));
        }
    }

    public JsonMessages getMessages(int from) throws IOException {

        HttpURLConnection conn = initConnection(BASE_URL + "/message?from=" + from, "GET", true);

        try (InputStream is = conn.getInputStream()) {
            byte[] buf = requestBodyToArray(is);
            String strBuf = new String(buf, StandardCharsets.UTF_8);
            JsonMessages list = gson.fromJson(strBuf, JsonMessages.class);
            return list;
        }
    }

    private byte[] requestBodyToArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }

    public int sendMessage(String from, String text) {

        Message msg = new Message(from, text);
        int result = 418;

        try {
            HttpURLConnection conn = initConnection(BASE_URL + "/message", "POST", true);
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(gson.toJson(msg).getBytes(StandardCharsets.UTF_8));
            }
            result = conn.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
