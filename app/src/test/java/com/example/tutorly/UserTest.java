package com.example.tutorly;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void getFirstName() {
        String input = "John";
        String expected = "John";

        User user = new User();
        user.setFirstName(input);

        assertEquals(expected, user.getFirstName());
    }

    @Test
    public void setFirstName() {
        String input = "Jane";
        String expected = "Jane";

        User user = new User();
        user.setFirstName(input);

        assertEquals(expected, user.getFirstName());
    }

    @Test
    public void getLastName() {
        String input = "Deer";
        String expected = "Deer";

        User user = new User();
        user.setLastName(input);

        assertEquals(expected, user.getLastName());
    }

    @Test
    public void setLastName() {
        String input = "Doe";
        String expected = "Doe";

        User user = new User();
        user.setLastName(input);

        assertEquals(expected, user.getLastName());
    }

    @Test
    public void getEmail() {
        String input = "test@crimson.ua.edu";
        String expected = "test@crimson.ua.edu";

        User user = new User();
        user.setEmail(input);

        assertEquals(expected, user.getEmail());
    }

    @Test
    public void setEmail() {
        String input = "test@crimson.ua.edu";
        String expected = "test@crimson.ua.edu";

        User user = new User();
        user.setEmail(input);

        assertEquals(expected, user.getEmail());
    }

    @Test
    public void getUniversity() {
        String input = "University of Alabama";
        String expected = "University of Alabama";

        User user = new User();
        user.setUniversity(input);

        assertEquals(expected, user.getUniversity());
    }

    @Test
    public void setUniversity() {
        String input = "University of Alabama";
        String expected = "University of Alabama";

        User user = new User();
        user.setUniversity(input);

        assertEquals(expected, user.getUniversity());
    }

    @Test
    public void getFullName() {
        String firstName = "Junie";
        String lastName = "Jones";
        String expected = "Junie Jones";

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);

        assertEquals(expected, user.getFullName());
    }
}