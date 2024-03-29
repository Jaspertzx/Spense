package com.spense_be.spense.classes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.spense_be.spense.SpenseController;

//probably best to create a server class somewhere...
public class Server {

    private Connection connection;
    private Properties properties = new Properties();

    public Server() {
        try {
            this.properties.load(SpenseController.class.getClassLoader().getResourceAsStream("app.properties"));
            this.connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        } catch (IOException e) {
            // perhaps add server call?
            e.printStackTrace();
        } catch (SQLException e) {
            // perhaps add server call as well?
            e.printStackTrace();
        }
    }

    public ResultSet query(String queryString) throws SQLException {
        this.connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        PreparedStatement readStatement = connection.prepareStatement(queryString);
        return readStatement.executeQuery();
    }
}
