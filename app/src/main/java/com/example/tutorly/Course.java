package com.example.tutorly;

public class Course {

    private String courseTitle;
    private String courseCode;
    private int courseNumber;
    private String courseCodeAndNum;
    private String uid;

    public Course(String courseTitle, String courseCode, int courseNumber, String uid) {

        this.courseTitle = courseTitle;
        this.courseCode = courseCode;
        this.courseNumber = courseNumber;
        this.uid = uid;
    }

    public Course() {
        //empty constructor
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseTitle() {
        return this.courseTitle;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseCode() {
        return this.courseCode;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public int getCourseNumber() {
        return this.courseNumber;
    }

    public String getCourseCodeAndNum() {
        return this.courseCodeAndNum;
    }

    public void setCourseCodeAndNum() {
        String courseNum = Integer.toString(this.courseNumber);
        this.courseCodeAndNum = this.courseCode + courseNum;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
