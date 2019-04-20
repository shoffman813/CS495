package com.example.tutorly;

import com.example.tutorly.User;

import java.util.ArrayList;
import java.util.List;

/*Holds additional information of Users who are also Tutors*/
public class Tutor extends User {

    public User user; //Linked to their user class
    private String shortBio;
    private List<String> tutoringClasses; //Classes the Tutor has actually taken
    private int yearsExperience; //Years of tutoring experience

    public Tutor (User user, String shortBio, List<String> tutoringClasses, int yearsExperience) {

        //How to handle user attributes???)
        this.user = user;
        this.shortBio = shortBio;
        this.tutoringClasses = new ArrayList<String>();
        this.yearsExperience = yearsExperience;
    }

    public Tutor () {
        //empty constructor
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

    public List<String> getClassList() {
        return this.tutoringClasses;
    }

    public void addClass(String newClass) {
        this.tutoringClasses.add(newClass);
    }

    public void removeClass(String oldClass) {
        this.tutoringClasses.remove(oldClass);
    }

    public int getYearsExperience() {
        return this.yearsExperience;
    }

    public void setYearsExperience(int yearsExperience) {
        this.yearsExperience = yearsExperience;
    }
}
