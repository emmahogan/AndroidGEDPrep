package com.emmahogan.gedcourse.quiz;

import android.provider.BaseColumns;

public final class QuizContract {

    private int unit_num;
    private int lesson_num;

    private static String qc_table_name = "quiz_questions";

    private QuizContract(int unit_num, int lesson_num) {
        this.unit_num = unit_num;
        this.lesson_num = lesson_num;

        qc_table_name = "questions_" + unit_num + "_" + lesson_num;
    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = qc_table_name;
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_ANSWER_POS = "answer_pos";
    }
}
