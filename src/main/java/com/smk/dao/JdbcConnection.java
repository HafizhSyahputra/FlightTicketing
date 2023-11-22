package com.smk.dao;

import javax.swing.text.html.Option;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class JdbcConnection {
    private static Optional<Connection> connection = Optional.empty();

    public static Optional<Connection> getConnection() {
        if (connection.isEmpty()) {
            String url = "jdbc:postgresql://localhost:5432/FlightBooking";
            String user = "FlightBooking";
            String password = "Fl19ht800k1n9";
            try {
                Class.forName("org.postgresql.Driver");
                connection = Optional.ofNullable(DriverManager.getConnection(url, user, password));
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}

