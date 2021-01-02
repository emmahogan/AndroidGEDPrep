package com.emmahogan.gedcourse;


import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Random;

public class Lesson {

    // Lesson string contents
    String title;
    String content;
    String example;
    String citation;

    // Number of lessons total in the current unit
    int num_lessons_in_unit;

    // Unit number and lesson number
    int unit_num;
    int lesson_num;

    // Practice questions for lesson
    ArrayList<Question> practice_questions = new ArrayList<Question>();

    // Whether user has passed this lesson or not
    Boolean passed;

    Random rand = new Random();

    // Context
    Context context;

    /* Constructor for a Lesson object */

    public Lesson(Context context, int unit, int lesson_num) {

        this.unit_num = unit;
        this.lesson_num = lesson_num;
        this.context = context;

        populate();

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

        String[] title_arr = r.getStringArray(r.getIdentifier("subtopic_names_" + unit_num, "array", context.getPackageName()));
        String[] content_arr = r.getStringArray(r.getIdentifier("subtopic_content_" + unit_num, "array", context.getPackageName()));
        String[] example_arr = r.getStringArray(r.getIdentifier("examples_" + unit_num, "array", context.getPackageName()));
        String[] citations_arr = r.getStringArray(r.getIdentifier("citations_" + unit_num, "array", context.getPackageName()));

        this.title = title_arr[this.lesson_num - 1];
        this.content = content_arr[this.lesson_num - 1];
        this.example = example_arr[this.lesson_num - 1];
        this.citation = citations_arr[this.lesson_num - 1];

        this.num_lessons_in_unit = title_arr.length;
    }
}
