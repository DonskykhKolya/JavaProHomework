package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.model.User;

import java.io.IOException;
import java.io.RandomAccessFile;


public class Test {

    public static void execute() {

        String json = "";

        try (RandomAccessFile file = new RandomAccessFile("src\\ua\\kiev\\prog\\json.txt", "r")) {
            byte[] buffer = new byte[(int)file.length()];
            file.read(buffer);
            json = new String(buffer);
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        if(json.isEmpty()) {
            System.out.println("Empty file.");
            return;
        }

        Gson gson = new GsonBuilder().create();
        User user = gson.fromJson(json, User.class);

        System.out.println(user);
    }
}
