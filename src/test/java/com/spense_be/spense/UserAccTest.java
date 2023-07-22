package com.spense_be.spense;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.spense_be.spense.classes.UserAcc;



public class UserAccTest {

    private UserAcc user;

    @Before
    public void setUp() {
        user = new UserAcc(1L, "john_doe", "password123", "john.doe@example.com", 123456789, 1634629812, "random_salt", 1);
    }

    @Test
    public void testGetters() {
        assertEquals(1L, user.getId().longValue());
        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.retrievePassword());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals(123456789, user.getMobilePhone());
        assertEquals("random_salt", user.retrieveSalt());
        assertEquals(1, user.getBusinessMode());
    }

    @Test
    public void testCheckPassword() {
        assertTrue(user.checkPassword("password123")); // Correct password
        assertFalse(user.checkPassword("wrong_password")); // Incorrect password
    }
}