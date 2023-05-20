package com.backend.sp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.backend.sp.classes.Todo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello";
    }

    // Get all Users
    @RequestMapping("/dbGetTest")
    @GetMapping
    public String getAllUsers() throws SQLException {
        ResultSet resultSet = runDatabaseQuery("SELECT * FROM todo;");
        if (!resultSet.next()) {
            return null;
        }
        Todo todo = new Todo();
        todo.setId(resultSet.getLong("id"));
        todo.setDescription(resultSet.getString("description"));
        todo.setDetails(resultSet.getString("details"));
        todo.setDone(resultSet.getBoolean("done"));
        return todo.toString();
    }

    @RequestMapping("/login/{ans}")
    @ResponseBody

    public ResultSet runDatabaseQuery(String queryString) throws SQLException {
        Properties properties = new Properties();
        try {
            properties.load(SpController.class.getClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        PreparedStatement readStatement = connection.prepareStatement(queryString);
        return readStatement.executeQuery();
    }
}
