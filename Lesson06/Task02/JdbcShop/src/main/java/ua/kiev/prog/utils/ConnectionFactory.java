package ua.kiev.prog.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {

    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/shop_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345";

    public static Connection getConnection() {

        Connection conn = null;
        try {
            conn =  DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
