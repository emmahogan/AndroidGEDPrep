package com.emmahogan.gedcourse;


import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Random;

public class Lesson {

    // Lesson title
    String title;

    // Unit number and lesson number
    int unit_num;
    int lesson_num;

    // Definitions in lesson
    ArrayList<String[]> definitions = new ArrayList<String[]>();

    // Practice questions for lesson
    ArrayList<Question> practice_questions = new ArrayList<Question>();

    // Whether user has passed this lesson or not
    Boolean passed;

    Random rand = new Random();

    // Context
    Context context;

    /* Constructor for a Lesson object */

    public Lesson(String title, int unit, int lesson_num, ArrayList<String[]> definitions, ArrayList<Question> questions) {

        this.title = title;
        this.unit_num = unit;
        this.lesson_num = lesson_num;
        this.definitions = definitions;
        this.practice_questions = questions;

        this.passed = false;

    }


    /* Get random questions (number specified) from the arraylist of practice questions
    associated with this lesson, returned as an ArrayList of questions.
     */

    public Question[] getRandomQuestions(int num_questions) {

        int totalQuestions = practice_questions.size();
        int pos;

        ArrayList<Integer> nums_taken = new ArrayList<Integer>();

        int max = num_questions;
        if(max > totalQuestions) {
            max = totalQuestions;
        }

        Question[] return_questions = new Question[max];

        for(int i=0; i<max; i++){

            pos = rand.nextInt(totalQuestions);
            while(nums_taken.contains(pos)) {
                pos = rand.nextInt(totalQuestions);
            }

            nums_taken.add(pos);
            return_questions[i] = practice_questions.get(pos);
        }

        return return_questions;
    }

    /* Populates the lessons with strings from resources */
    public void populate() {
        Resources r = context.getResources();

    }
}
