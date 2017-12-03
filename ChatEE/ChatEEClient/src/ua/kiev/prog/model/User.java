package ua.kiev.prog.model;


import java.util.List;


public class User {

    private String login;
    private String password;
    private String sessionId;
    private Status status;
    private List<User> contacts;

    public User() { }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<User> getContacts() {
        return this.contacts;
    }

    public boolean addContact(User user) {
        boolean duplicate = this.contacts.contains(user);
        if(!duplicate) {
            this.contacts.add(user);
        }
        return !duplicate;
    }
}
