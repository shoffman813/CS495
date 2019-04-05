package com.example.tutorly;

import org.junit.Test;

import static org.junit.Assert.*;

public class SessionTest {

    @Test
    public void getTutorName() {
        String input = "John Smith";
        String expected = "John Smith";

        Session session = new Session();
        session.setTutorName(input);

        assertEquals(expected, session.getTutorName());

    }

    @Test
    public void setTutorName() {
        String input = "Jane Doe";
        String expected = "Jane Doe";

        Session session = new Session();
        session.setTutorName(input);

        assertEquals(expected, session.getTutorName());

    }

    @Test
    public void getMeetingMonth() {
        int input = 5;
        int expected = 5;

        Session session = new Session();
        session.setMeetingMonth(input);

        assertEquals(expected, session.getMeetingMonth());
    }

    @Test
    public void setMeetingMonth() {
        int input = 10;
        int expected = 10;

        Session session = new Session();
        session.setMeetingMonth(input);

        assertEquals(expected, session.getMeetingMonth());
    }

    @Test
    public void getMeetingDay() {
        int input = 14;
        int expected = 14;

        Session session = new Session();
        session.setMeetingDay(input);

        assertEquals(expected, session.getMeetingDay());

    }

    @Test
    public void setMeetingDay() {
        int input = 30;
        int expected = 30;

        Session session = new Session();
        session.setMeetingDay(input);

        assertEquals(expected, session.getMeetingDay());
    }

    @Test
    public void getMeetingHour() {
        int input = 2;
        int expected = 2;

        Session session = new Session();
        session.setMeetingHour(input);

        assertEquals(expected, session.getMeetingHour());
    }

    @Test
    public void setMeetingHour() {
        int input = 4;
        int expected = 4;

        Session session = new Session();
        session.setMeetingHour(input);

        assertEquals(expected, session.getMeetingHour());
    }

    @Test
    public void getMeetingMinute() {
        int input = 30;
        int expected = 30;

        Session session = new Session();
        session.setMeetingMinute(input);

        assertEquals(expected, session.getMeetingMinute());
    }

    @Test
    public void setMeetingMinute() {
        int input = 45;
        int expected = 45;

        Session session = new Session();
        session.setMeetingMinute(input);

        assertEquals(expected, session.getMeetingMinute());
    }

    @Test
    public void getMeetingLocation() {
        String input = "Gorgas Library";
        String expected = "Gorgas Library";

        Session session = new Session();
        session.setMeetingLocation(input);

        assertEquals(expected, session.getMeetingLocation());
    }

    @Test
    public void setMeetingLocation() {
        String input = "Rodgers Library";
        String expected = "Rodgers Library";

        Session session = new Session();
        session.setMeetingLocation(input);

        assertEquals(expected, session.getMeetingLocation());
    }
}