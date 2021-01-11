package com.emmahogan.gedcourse;

public class MultipleChoiceQuestion{

    public String question;
    public String option1;
    public String option2;
    public String option3;
    public int answer_pos;

    public MultipleChoiceQuestion() {}

    public MultipleChoiceQuestion(String question, String option1, String option2, String option3, int answer_pos) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answer_pos = answer_pos;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public int getAnswer_pos() {
        return answer_pos;
    }

    public void setAnswer_pos(int answer_pos) {
        this.answer_pos = answer_pos;
    }
}
