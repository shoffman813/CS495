package com.example.tutorly;

import com.example.tutorly.User;

import java.util.ArrayList;
import java.util.List;

/*Holds additional information of Users who are also Tutors*/
public class Tutor extends User {

    private List<String> tutoringClasses; //Classes the Tutor has actually taken
    private int yearsExperience; //Years of tutoring experience

    public Tutor (User user, List<String> tutoringClasses, int yearsExperience) {

        //How to handle user attributes???
        this.tutoringClasses = new ArrayList<String>();
        this.yearsExperience = yearsExperience;
       //add user ID??
    }

    public Tutor () {
        //empty constructor
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
