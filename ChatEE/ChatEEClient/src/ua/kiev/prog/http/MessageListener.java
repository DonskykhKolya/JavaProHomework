package ua.kiev.prog.http;

import ua.kiev.prog.model.JsonMessages;
import ua.kiev.prog.model.Message;


public class MessageListener implements Runnable {

    private int from;
    private HttpClient httpClient;

    public MessageListener(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                JsonMessages list = httpClient.getMessages(from);
                if (list != null) {
                    for (Message message : list.getList()) {
                        System.out.println(message);
                        from++;
                    }
                }
                Thread.sleep(500);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
