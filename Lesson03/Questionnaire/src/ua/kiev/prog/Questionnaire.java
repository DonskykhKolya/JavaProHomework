package ua.kiev.prog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Questionnaire {

    private static final String KEY_Q1_Y = "q1y";
    private static final String KEY_Q1_N = "q1n";
    private static final String KEY_Q2_Y = "q2y";
    private static final String KEY_Q2_N = "q2n";

    private static final Map<String, Integer> statistics = new HashMap<>();
    private static final List<User> users = new ArrayList<>();

    static {
        statistics.put(KEY_Q1_Y, 0);
        statistics.put(KEY_Q1_N, 0);
        statistics.put(KEY_Q2_Y, 0);
        statistics.put(KEY_Q2_N, 0);
    }


    public static void save(String firstname, String lastname, int age, String q1, String q2) {

        User user = new User(firstname, lastname, age);
        users.add(user);

        addStatistics(q1, q2);
    }

    private static void addStatistics(String q1, String q2) {

        int q1y = statistics.get(KEY_Q1_Y);
        int q1n = statistics.get(KEY_Q1_N);
        if (q1.equals("yes")) {
            statistics.put(KEY_Q1_Y, ++q1y);
        } else {
            statistics.put(KEY_Q1_N, ++q1n);
        }

        int q2y = statistics.get(KEY_Q2_Y);
        int q2n = statistics.get(KEY_Q2_N);
        if (q2.equals("yes")) {
            statistics.put(KEY_Q2_Y, ++q2y);
        } else {
            statistics.put(KEY_Q2_N, ++q2n);
        }
    }

    public static String getStatistics() {

        StringBuilder sb = new StringBuilder();

        int q1y = statistics.get(KEY_Q1_Y);
        int q1n = statistics.get(KEY_Q1_N);
        int q2y = statistics.get(KEY_Q2_Y);
        int q2n = statistics.get(KEY_Q2_N);

        sb.append(q1y).append(" people love dogs, and ").append(q1n).append(" do not like them.").append("<br>")
                .append(q2y).append(" people love cats, and ").append(q2n).append(" do not like them.");
        return sb.toString();
    }

}
