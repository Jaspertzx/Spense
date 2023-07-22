package com.spense_be.spense;

import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectivityTest {

    @Test
    public void testDatabaseConnectivity() {
        Properties properties = new Properties();
        try {
            properties.load(SpenseController.class.getClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }        
        try {
            Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
