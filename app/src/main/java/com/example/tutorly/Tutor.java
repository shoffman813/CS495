package com.example.tutorly;

import com.example.tutorly.User;

import java.util.ArrayList;
import java.util.List;

public class Tutor extends User {

    private List<String> tutoringClasses;
    private int yearsExperience;

    public Tutor (User user, List<String> tutoringClasses, int yearsExperience) {

        //How to handle user attributes???
        this.tutoringClasses = new ArrayList<String>();
        this.yearsExperience = yearsExperience;
       //add user ID??
    }

    public Tutor () {

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
}
