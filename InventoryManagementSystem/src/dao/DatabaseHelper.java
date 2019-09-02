package dao;

import java.sql.*;

public class DatabaseHelper {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://"
                    + "localhost:3306/test_24", "root", "7487");
            return con;
        } catch (Exception e) {
            System.err.println("Connection Error");
            return null;
        }

    }
}
