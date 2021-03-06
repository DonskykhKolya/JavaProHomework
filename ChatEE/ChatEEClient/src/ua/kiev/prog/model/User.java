package ua.kiev.prog.model;


public class User {

    private String login;
    private String password;
    private Status status;
    private String sessionId;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSessionId() { return sessionId;  }

    public void setSessionId(String sessionId) { this.sessionId = sessionId; }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", status=" + status +
                '}';
    }
}
