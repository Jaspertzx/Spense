package com.spense_be.spense;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Base64;
import java.util.Properties;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spense_be.spense.classes.UserAcc;

@RestController
public class SpenseUserController {

    @PostMapping("/loginRequest")
    @ResponseBody
    public String loginRequest(@RequestBody UserAcc u)
            throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        String jsonStr = null;
        String dbCheck = "SELECT * FROM Users WHERE username ='" + u.getUsername() + "'";
        System.out.println("check1" + u.getEmail());
        ResultSet rs = runDatabaseQuery(dbCheck);
        System.out.println("check2");
        UserAcc ua = null;
        ObjectMapper mapper = new ObjectMapper();
        while (rs.next()) {
            ua = new UserAcc(rs.getLong("id"), rs.getString("username"), rs.getString("password"),
                    rs.getString("email"),
                    rs.getInt("mobilePhone"), rs.getInt("date"), rs.getString("salt"), rs.getInt("businessMode"));
            String hp = getHashedPassword(u.retrievePassword(), base64Decode(ua.retrieveSalt()));
            
            // convert user object to JSON String
            try {
                jsonStr = mapper.writeValueAsString(ua);
                System.out.println(jsonStr);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            if (ua.checkPassword(hp)) {
                return jsonStr;
            }
        }
        return null;
    }

    @PostMapping("/signUpRequest" )
    @ResponseBody
    public String signUpRequest(@RequestBody UserAcc ua)
            throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        long time = Instant.now().getEpochSecond();
        byte[] salt = getSalt();
        String hashedPassword = getHashedPassword(ua.retrievePassword(), salt);
        String dbCheck = "SET NOCOUNT ON INSERT INTO Users (username, password, email, mobilePhone, date, salt, businessMode) VALUES ('"
                + ua.getUsername()
                + "', '" + hashedPassword + "', '" + ua.getEmail() + "', " + ua.getMobilePhone() + ", " + time + ", '"
                + base64Encode(salt)
                + "' ," + ua.getBusinessMode() + ");";

        System.out.println(dbCheck);
        int rowsAffected = runDatabaseInsertQuery(dbCheck);
        System.out.println(rowsAffected);
        return "created";
    }

    public int runDatabaseInsertQuery(String queryString) throws SQLException {
        Properties properties = new Properties();
        try {
            properties.load(SpenseController.class.getClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        System.out.println("after connect");
        PreparedStatement updateStatement = connection.prepareStatement(queryString);
        System.out.println("after read");
        // returns the number of rows affected
        int rowsAffected = updateStatement.executeUpdate();
        return rowsAffected;
    }


    public ResultSet runDatabaseQuery(String queryString) throws SQLException {
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
        return readStatement.executeQuery();
    }

    private static String getHashedPassword(String rawPassword, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(rawPassword.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return base64Encode(hash);
    }

    private static byte[] getSalt() {
        Random r = new SecureRandom();
        byte[] saltBytes = new byte[32];
        r.nextBytes(saltBytes);
        return saltBytes;
    }

    private static String base64Encode(byte[] src) {
        return Base64.getEncoder().encodeToString(src);
    }

    private static byte[] base64Decode(String src) {
        return Base64.getDecoder().decode(src);
    }
}
