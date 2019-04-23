package com.example.tutorly;

/*Class to hold all information about a scheduled/requested session*/
public class Session {

    private String tutorName;
    private int meetingMonth;
    private int meetingDay;
    private int meetingHour;
    private int meetingMinute;
    private String meetingLocation;

    private boolean isRequested; //When true, shows up in user/tutor's requested screen
    private boolean isConfirmed; //When true, shows up in user's scheduled screen

    /*Class constructor*/
    public Session(String tutorName, int meetingMonth, int meetingDay, int meetingHour,
                   int meetingMinute, String meetingLocation) {
        this.tutorName = tutorName;
        this.meetingMonth = meetingMonth;
        this.meetingDay = meetingDay;
        this.meetingHour = meetingHour;
        this.meetingMinute = meetingMinute;
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

    public int getMeetingHour() { //Method to get the meeting hour
        return this.meetingHour;
    }

    public void setMeetingHour(int meetingHour) { //Method to set the meeting hour
        if((meetingHour >= 0) && (meetingHour <24)) this.meetingHour = meetingHour;
        else throw new IllegalArgumentException("Invalid hour value");
    }

    public int getMeetingMinute() { //Method to get the meeting minute
        return this.meetingMinute;
    }

    public void setMeetingMinute(int meetingMinute) { //Method to set the meeting minute
        if((meetingMinute >= 0) && (meetingMinute < 60)) this.meetingMinute = meetingMinute;
        else throw new IllegalArgumentException("Invalid minute value");
    }

    public String getMeetingLocation() { //Method to get the meeting location
        return this.meetingLocation;
    }

    public void setMeetingLocation(String meetingLocation) { //Method to set the meeting location
        this.meetingLocation = meetingLocation;
    }
}
