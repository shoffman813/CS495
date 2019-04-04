package com.example.tutorly;

public class Session {

    private String tutorName;
    private int meetingMonth;
    private int meetingDay;
    private int meetingHour;
    private int meetingMinute;
    private String meetingLocation;

    public Session(String tutorName, int meetingMonth, int meetingDay, int meetingHour,
                   int meetingMinute, String meetingLocation) {
        this.tutorName = tutorName;
        this.meetingMonth = meetingMonth;
        this.meetingDay = meetingDay;
        this.meetingHour = meetingHour;
        this.meetingMinute = meetingMinute;
        this.meetingLocation = meetingLocation;
    }

    public String getTutorName() {
        return this.tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public int getMeetingMonth() {
        return this.meetingMonth;
    }

    public void setMeetingMonth(int meetingMonth) {
        this.meetingMonth = meetingMonth;
    }

    public int getMeetingDay() {
        return this.meetingDay;
    }

    public void setMeetingDay(int meetingDay) {
        this.meetingDay = meetingDay;
    }

    public int getMeetingHour() {
        return this.meetingHour;
    }

    public void setMeetingHour(int meetingHour) {
        this.meetingHour = meetingHour;
    }

    public int getMeetingMinute() {
        return this.meetingMinute;
    }

    public void setMeetingMinute(int meetingMinute) {
        this.meetingMinute = meetingMinute;
    }

    public String getMeetingLocation() {
        return this.meetingLocation;
    }

    public void setMeetingLocation(String meetingLocation) {
        this.meetingLocation = meetingLocation;
    }
}
