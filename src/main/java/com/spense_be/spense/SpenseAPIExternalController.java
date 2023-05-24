package com.spense_be.spense;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spense_be.spense.classes.Business;
import com.spense_be.spense.classes.Server;

@RestController
public class SpenseAPIExternalController {

    Server server = new Server();

    @RequestMapping("/pingSpense")
    public String pingSpense() {
        return "I am awake";
    }

    @RequestMapping("/createBusinessAcc")
    @ResponseBody
    public String createBusinessAcc(@RequestBody Business ua) throws SQLException {
        ResultSet resultSet = server.query("SELECT * FROM USERS;");
        return "Creating: " + resultSet.next();
    }

}
