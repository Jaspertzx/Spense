package com.spense_be.spense;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.spense_be.spense.classes.Todo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpenseController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello25th";
    }

    // Get all Users
    @RequestMapping("/dbGetTest")
    @GetMapping
    public String getAllUsers() throws SQLException {
        ResultSet resultSet = runDatabaseQuery("SELECT * FROM todo;");
        List<Todo> ls = new ArrayList<Todo>();
        while (resultSet.next()) {
            ls.add(new Todo(resultSet.getString("description"), resultSet.getString("details"),
                    resultSet.getBoolean("done")));
        }
        return ls.size() + "";
    }

    public ResultSet runDatabaseQuery(String queryString) throws SQLException {
        Properties properties = new Properties();
        try {
            properties.load(SpenseController.class.getClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        PreparedStatement readStatement = connection.prepareStatement(queryString);
        return readStatement.executeQuery();
    }
}
