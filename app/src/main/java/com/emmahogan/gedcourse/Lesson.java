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
    String anim_uri_str;

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

        String[] title_arr;
        String[] content_arr;
        String[] example_arr;
        String[] citations_arr;
        String[] anim_arr;

        switch(this.unit_num) {

            case 2:
                title_arr = r.getStringArray(R.array.subtopic_names_2);
                content_arr = r.getStringArray(R.array.subtopic_content_2);
                example_arr = r.getStringArray(R.array.examples_2);
                citations_arr = r.getStringArray(R.array.citations_2);
                anim_arr = r.getStringArray(R.array.animations_1);

                break;

            case 3:
                title_arr = r.getStringArray(R.array.subtopic_names_3);
                content_arr = r.getStringArray(R.array.subtopic_content_3);
                example_arr = r.getStringArray(R.array.examples_3);
                citations_arr = r.getStringArray(R.array.citations_3);
                anim_arr = r.getStringArray(R.array.animations_1);

                break;

            case 4:
                title_arr = r.getStringArray(R.array.subtopic_names_4);
                content_arr = r.getStringArray(R.array.subtopic_content_4);
                example_arr = r.getStringArray(R.array.examples_4);
                citations_arr = r.getStringArray(R.array.citations_4);
                anim_arr = r.getStringArray(R.array.animations_1);

                break;

            case 5:
                title_arr = r.getStringArray(R.array.subtopic_names_5);
                content_arr = r.getStringArray(R.array.subtopic_content_5);
                example_arr = r.getStringArray(R.array.examples_5);
                citations_arr = r.getStringArray(R.array.citations_5);
                anim_arr = r.getStringArray(R.array.animations_1);

                break;

            case 6:
                title_arr = r.getStringArray(R.array.subtopic_names_6);
                content_arr = r.getStringArray(R.array.subtopic_content_6);
                example_arr = r.getStringArray(R.array.examples_6);
                citations_arr = r.getStringArray(R.array.citations_6);
                anim_arr = r.getStringArray(R.array.animations_1);

                break;

            case 7:
                title_arr = r.getStringArray(R.array.subtopic_names_7);
                content_arr = r.getStringArray(R.array.subtopic_content_7);
                example_arr = r.getStringArray(R.array.examples_7);
                citations_arr = r.getStringArray(R.array.citations_7);
                anim_arr = r.getStringArray(R.array.animations_1);

                break;

            case 8:
                title_arr = r.getStringArray(R.array.subtopic_names_8);
                content_arr = r.getStringArray(R.array.subtopic_content_8);
                example_arr = r.getStringArray(R.array.examples_8);
                citations_arr = r.getStringArray(R.array.citations_8);
                anim_arr = r.getStringArray(R.array.animations_1);

                break;

            case 9:
                title_arr = r.getStringArray(R.array.subtopic_names_9);
                content_arr = r.getStringArray(R.array.subtopic_content_9);
                example_arr = r.getStringArray(R.array.examples_9);
                citations_arr = r.getStringArray(R.array.citations_9);
                anim_arr = r.getStringArray(R.array.animations_1);

                break;

            case 10:
                title_arr = r.getStringArray(R.array.subtopic_names_10);
                content_arr = r.getStringArray(R.array.subtopic_content_10);
                example_arr = r.getStringArray(R.array.examples_10);
                citations_arr = r.getStringArray(R.array.citations_10);
                anim_arr = r.getStringArray(R.array.animations_1);

                break;

            case 11:
                title_arr = r.getStringArray(R.array.subtopic_names_11);
                content_arr = r.getStringArray(R.array.subtopic_content_11);
                example_arr = r.getStringArray(R.array.examples_11);
                citations_arr = r.getStringArray(R.array.citations_11);
                anim_arr = r.getStringArray(R.array.animations_1);

                break;

            default:
                title_arr = r.getStringArray(R.array.subtopic_names_1);
                content_arr = r.getStringArray(R.array.subtopic_content_1);
                example_arr = r.getStringArray(R.array.examples_1);
                citations_arr = r.getStringArray(R.array.citations_1);
                anim_arr = r.getStringArray(R.array.animations_1);

                break;
        }

        this.title = title_arr[this.lesson_num - 1];
        this.content = content_arr[this.lesson_num - 1];
        this.example = example_arr[this.lesson_num - 1];
        this.citation = citations_arr[this.lesson_num - 1];
        this.anim_uri_str = anim_arr[this.lesson_num - 1];

        this.num_lessons_in_unit = title_arr.length;
    }
}
