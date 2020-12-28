package com.emmahogan.gedcourse;

public class Question {

    public String question;
    public String answer;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;

    }

    public boolean checkAnswer(String user_answer) {
        return user_answer.equals(answer);
    }

}
