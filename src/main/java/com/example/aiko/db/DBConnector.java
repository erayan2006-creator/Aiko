package com.example.aiko.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
    private static Connection connection;
    private static String log = "postgres";
    private static String pas = "08123469";
    private static String url = "jdbc:postgresql://localhost:5432/postgres";

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, log, pas);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
