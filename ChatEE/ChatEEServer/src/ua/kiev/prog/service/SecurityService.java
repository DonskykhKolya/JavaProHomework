package ua.kiev.prog.service;

import ua.kiev.prog.storage.UserList;

import javax.servlet.http.Cookie;


public class SecurityService {

    private static UserList userList = UserList.getInstance();

    public static String check(String login, String password) {
        return userList.check(login, password);
    }

    public static boolean validate(Cookie[] cookies) {

        String sessionId = "";

        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("session_id")){
                    sessionId = cookie.getValue();
                    break;
                }
            }
        }

        return userList.isValid(sessionId);
    }
}
