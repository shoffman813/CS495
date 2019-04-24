package com.example.tutorly;

import java.sql.Time;

/*Class to hold all information about a scheduled/requested session*/
public class Session {

    private String tutorName;
    private String userName;
    private int meetingMonth;
    private int meetingDay;
    private int meetingYear;
    private String meetingStartTime;
    private String meetingEndTime;
    private String meetingLocation;
    private String sessionRequestMessage;

    public boolean isRequested; //When true, shows up in user/tutor's requested screen
    public boolean isConfirmed; //When true, shows up in user's scheduled screen
    public boolean isDenied; //When true, session is dismissed from user/tutor requested screen

    private String tutorUid;
    private String userUid; //UID of user who requested

    /*Class constructor*/
    public Session(String tutorName, int meetingMonth, int meetingDay, String meetingStartTime,
                   String meetingEndTime, String meetingLocation) {
        this.tutorName = tutorName;
        this.meetingMonth = meetingMonth;
        this.meetingDay = meetingDay;
        this.meetingStartTime = meetingStartTime;
        this.meetingEndTime = meetingEndTime;
        this.meetingLocation = meetingLocation;
    }

    public Session() {
        //empty constructor
    }

    public String getTutorName() { //Method to get the name of tutor
        return this.tutorName;
    }

    public void setTutorName(String tutorName) { //Method to set the name of tutor
        this.tutorName = tutorName;
    }

    public int getMeetingMonth() { //Method to get the meeting month
        return this.meetingMonth;
    }

    public void setMeetingMonth(int meetingMonth) { //Method to set the meeting month

        if ((meetingMonth > 0) && (meetingMonth < 13)) this.meetingMonth = meetingMonth;
        else throw new IllegalArgumentException("Invalid month value");
    }

    public int getMeetingDay() { //Method to get the meeting day
        return this.meetingDay;
    }

    public void setMeetingDay(int meetingDay) { //Method to set the meeting day
        if((meetingDay > 0) && (meetingDay < 31)) this.meetingDay = meetingDay;
        else throw new IllegalArgumentException("Invalid day value");
    }

    public String getMeetingStartTime() { //Method to get the meeting hour
        return this.meetingStartTime;
    }

    public void setMeetingStartTime(String meetingStartTime) { //Method to set the meeting hour
        this.meetingStartTime = meetingStartTime;
    }

    public String getMeetingLocation() { //Method to get the meeting location
        return this.meetingLocation;
    }

    public void setMeetingLocation(String meetingLocation) { //Method to set the meeting location
        this.meetingLocation = meetingLocation;
    }

    public int getMeetingYear() {
        return meetingYear;
    }

    public void setMeetingYear(int meetingYear) {
        this.meetingYear = meetingYear;
    }

    public String getSessionRequestMessage() {
        return sessionRequestMessage;
    }

    public void setSessionRequestMessage(String sessionRequestMessage) {
        this.sessionRequestMessage = sessionRequestMessage;
    }

    public String getTutorUid() {
        return tutorUid;
    }

    public void setTutorUid(String tutorUid) {
        this.tutorUid = tutorUid;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getMeetingEndTime() {
        return meetingEndTime;
    }

    public void setMeetingEndTime(String meetingEndTime) {
        this.meetingEndTime = meetingEndTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
