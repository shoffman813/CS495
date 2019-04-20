package com.example.tutorly;

import com.example.tutorly.User;

import java.util.ArrayList;
import java.util.List;

/*Holds additional information of Users who are also Tutors*/
public class Tutor extends User {

    public User user; //Linked to their user class
    private String shortBio;
    private ArrayList<Course> tutoringClasses; //Classes the Tutor has actually taken

    public Tutor (User user, String shortBio, ArrayList<Course> tutoringClasses) {

        //How to handle user attributes???)
        this.user = user;
        this.shortBio = shortBio;
        this.tutoringClasses = tutoringClasses;
    }

    public Tutor () {
        //empty constructor
        this.tutoringClasses = new ArrayList<>();
    }

    public User getUserApp() {
        return this.user;
    }

    public void setUserApp(User user) {
        this.user = user;
    }

    public String getShortBio() {
        return this.shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

    public ArrayList<Course> getClassList() {
        return this.tutoringClasses;
    }

    public void addClass(Course newClass) {
        this.tutoringClasses.add(newClass);
    }

    public void removeClass(Course oldClass) {
        this.tutoringClasses.remove(oldClass);
    }
}
