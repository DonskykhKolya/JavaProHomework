package ua.kiev.prog;

import ua.kiev.prog.http.HttpClient;
import ua.kiev.prog.http.MessageListener;

import java.net.HttpURLConnection;
import java.util.Scanner;


public class Chat {

    private HttpClient httpClient;
    private Scanner scanner;
    private String login;

    public Chat() {
        httpClient = new HttpClient();
        scanner = new Scanner(System.in);
    }

    public void login() {

        System.out.println("Enter your login: ");
        login = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        if(!httpClient.login(login, password)) {
            System.out.println("Incorrect login/password. Please, try again.");
            login();
        }
        else {
            start();
        }
    }

    private void start() {

        Thread th = new Thread(new MessageListener(httpClient));
        th.setDaemon(true);
        th.start();

        System.out.println("Enter your message: ");
        while (true) {
            String text = scanner.nextLine();
            if (text.isEmpty()) break;
            int result = httpClient.sendMessage(login, text);
            if (result != HttpURLConnection.HTTP_OK) {
                System.out.println("HTTP error occured: " + result);
                return;
            }
        }
    }
}
