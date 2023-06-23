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

import com.spense_be.spense.classes.UserAcc;

@RestController
public class SpenseUserController {

    @PostMapping("/loginRequest")
    @ResponseBody
    public Boolean loginRequest(@RequestBody UserAcc u)
            throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        String dbCheck = "SELECT * FROM Users WHERE username ='" + u.getUsername() + "'";
        System.out.println("check1");
        ResultSet rs = runDatabaseQuery(dbCheck);
        System.out.println("check2");
        UserAcc ua = null;
        while (rs.next()) {
            ua = new UserAcc(rs.getLong("id"), rs.getString("username"), rs.getString("password"),
                    rs.getString("email"),
                    rs.getInt("mobilePhone"), rs.getInt("date"), rs.getString("salt"));
            String hp = getHashedPassword(u.retrievePassword(), base64Decode(ua.retrieveSalt()));
            if (ua.checkPassword(hp)) {
                return true;
            }
        }
        return false;
    }

    @PostMapping("/signUpRequest")
    @ResponseBody
    public String signUpRequest(@RequestBody UserAcc ua)
            throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        long time = Instant.now().getEpochSecond();
        byte[] salt = getSalt();
        String hashedPassword = getHashedPassword(ua.retrievePassword(), salt);
        String dbCheck = "SET NOCOUNT ON INSERT INTO Users (username, password, email, mobilePhone, date, salt) VALUES ('"
                + ua.getUsername()
                + "', '" + hashedPassword + "', '" + ua.getEmail() + "', " + ua.getMobilePhone() + ", " + time + ", '"
                + base64Encode(salt)
                + "');";
        ResultSet rs = runDatabaseQuery(dbCheck);
        return "created";
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
