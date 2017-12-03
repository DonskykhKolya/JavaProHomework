package ua.kiev.prog;

import ua.kiev.prog.http.HttpClient;
import ua.kiev.prog.http.MessageListener;
import ua.kiev.prog.model.Status;
import ua.kiev.prog.model.User;

import java.net.HttpURLConnection;
import java.util.Scanner;


public class Chat {

    private HttpClient httpClient;
    private Scanner scanner;
    private User currUser;

    public Chat() {
        httpClient = new HttpClient();
        scanner = new Scanner(System.in);
    }

    public void login() {

        System.out.println("Enter your login: ");
        String login = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        if(!httpClient.login(login, password)) {
            System.out.println("Incorrect login/password. Please, try again.");
            login();
        }
        else {
            currUser = new User(login, password);
            currUser.setStatus(Status.ON);
            start();
        }
    }

    private void start() {

        Thread th = new Thread(new MessageListener(httpClient));
        th.setDaemon(true);
        th.start();

        System.out.println("Welcome, " + currUser.getLogin() + "!");
        while(true) {
            System.out.println("1: send message to all");
            System.out.println("2: send message to user");
            System.out.println("3: change status");
            System.out.println("4: show user list");
            System.out.println("->");
            String s = scanner.nextLine();

            if (s.isEmpty()) break;

            switch(s) {
                case "1":
                    sendMessage(null);
                    break;
                case "2":
                    System.out.println("Enter user name: ");
                    String name = scanner.nextLine();
                    sendMessage(name);
                    break;
                case "3":
                    changeStatus();
                    break;
                case "4":
                    showUserList();
                    break;
                default:
                    close();
            }
        }
    }

    private void sendMessage(String to) {

        System.out.println("Enter message text: ");
        String text = scanner.nextLine();
        int result = httpClient.sendMessage(currUser.getLogin(), to, text);
        if (result != HttpURLConnection.HTTP_OK) {
            System.out.println("HTTP error occured: " + result);
        }
    }

    private void changeStatus() {

        System.out.println("Your current status is: " + currUser.getStatus().toString());
        System.out.println("You can change your status to: ");
        System.out.println("'+' - ON");
        System.out.println("'-' - OFF");
        String s = scanner.nextLine();

        Status newStatus;
        switch(s){
            case "+":
                newStatus = Status.ON;
                break;
            case "-":
                newStatus = Status.OFF;
                break;
            default:
                return;
        }

        int result = httpClient.changeStatus(newStatus);
        if (result != HttpURLConnection.HTTP_OK) {
            System.out.println("HTTP error occured: " + result);
        }
        else {
            currUser.setStatus(newStatus);
            System.out.println("Your status changed. Current status is: " + newStatus);
        }
    }

    private void showUserList() {

    }

    private void close() {
        scanner.close();
    }
}
