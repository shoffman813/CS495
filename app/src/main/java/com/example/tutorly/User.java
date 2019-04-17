package com.example.tutorly;

public class User {

    private String userID;
    private String firstName;
    private String lastName;
    private String email; //Primary key
    private String university;
    private int isTutor; //Initially set to zero upon sign up

    /*Class constructor*/
    public User(String userID, String firstName, String lastName, String email, String university, int isTutor) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.university = university;
        this.isTutor = isTutor;
    }

    public User() {
        //empty constructor
    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        if(email.contains("@")) this.email = email;
        else throw new IllegalArgumentException("Invalid email value");
    }

    public String getUniversity() {
        return this.university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public int getIsTutor() {
        return this.isTutor;
    }

    public void setIsTutor(int isTutor) {
        this.isTutor = isTutor;
    }

    /*Other functions*/

    public String getFullName() {
        String fullName = this.firstName + " " + this.lastName;
        return fullName;
    }
}

