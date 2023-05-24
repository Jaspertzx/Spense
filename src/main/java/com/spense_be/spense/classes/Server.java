package com.spense_be.spense.classes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.spense_be.spense.SpenseController;

//probably best to create a server class somewhere...
public class Server {

    private Connection connection;

    public Server() throws SQLException {
        Properties properties = new Properties();
        try {
            properties.load(SpenseController.class.getClassLoader().getResourceAsStream("app.properties"));
            this.connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
