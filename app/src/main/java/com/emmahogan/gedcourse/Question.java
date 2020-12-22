package com.emmahogan.gedcourse;

public class Question {

    public String question;
    public String answer;
    public boolean multiple_choice;

    public Question(String question, String answer, boolean multiple_choice) {
        this.question = question;
        this.answer = answer;
        this.multiple_choice = multiple_choice;
    }

}
