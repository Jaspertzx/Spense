package com.spense_be.spense;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.spense_be.spense.classes.Business;
import com.spense_be.spense.classes.CreateReceiptWrapper;
import com.spense_be.spense.classes.Items;
import com.spense_be.spense.classes.Receipts;
import com.spense_be.spense.classes.Server;
import com.spense_be.spense.classes.Todo;
import com.spense_be.spense.classes.UserAcc;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ReceiptController {

    // Get all Users
    @PostMapping("/createReceipt")
    @ResponseBody
    public String createReceipt(@RequestBody CreateReceiptWrapper total) throws SQLException {
        Receipts r = total.receipts;
        Business b = total.business;
        UserAcc u = total.userAcc;
        System.out.println(r.getPaymentMethod());
        UUID uuid1 = UUID.randomUUID();
        String receipt_id = b.getId() + "-" + uuid1.toString();
        long time = Instant.now().getEpochSecond();
        this.runDatabaseQuery("INSERT INTO receipts (receiptID, dateissued, price, discount, paymentMethod, staffName, UserID, businessId) VALUES ('" + receipt_id + "', " + time + ", " + r.getPrice() + ", " + r.getDiscount() + ", '" + r.getPaymentMethod() + "', '" + r.getStaffName() + "', " + u.getId() + ", " + b.getId() + ");");
        Items[] items = r.getItems();
        String query = "INSERT INTO items (businessid, name, price, receiptId) VALUES ";
        for (int i = 0; i < items.length; i++) {
            query += "(" + b.getId() + ", '" + items[i].getName() + "', " + items[i].getPrice() + ", '" + receipt_id + "')";
            if (i + 1 != items.length) {
                query += ",";
            }
        }
        this.runDatabaseQuery(query);
        return "True";
    }

    public void runDatabaseQuery(String queryString) throws SQLException {
        Properties properties = new Properties();
        try {
            properties.load(SpenseController.class.getClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        System.out.println("after connect");
        PreparedStatement readStatement = connection.prepareStatement(queryString);
        System.out.println("after read");
        readStatement.executeUpdate();
    }    

}
