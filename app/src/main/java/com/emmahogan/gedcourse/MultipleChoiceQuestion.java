package com.emmahogan.gedcourse;

public class MultipleChoiceQuestion extends Question{

    public String[] options;
    public int answer_pos;

    public MultipleChoiceQuestion(String question, String[] options, int answer_pos) {
        super(question, options[answer_pos]);
        this.options = options;
        this.answer_pos = answer_pos;
    }
}