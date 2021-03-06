package com.emmahogan.gedcourse.quiz;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.emmahogan.gedcourse.MyApplication;
import com.emmahogan.gedcourse.instruction.Lesson;
import com.emmahogan.gedcourse.quiz.QuizContract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Quiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    Context context;

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.context = MyApplication.getAppContext();
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_POS + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        populateQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void populateQuestionsTable() {

        Resources r = context.getResources();

        // Get array of all unit lengths from resources
        int[] num_lessons = r.getIntArray(r.getIdentifier("num_lessons_per_unit", "array", context.getPackageName()));



        // For every lesson from every unit, add every practice question to the db
        for(int unit = 1; unit <= num_lessons.length; unit++){
            for(int lesson = 1; lesson <= num_lessons[unit-1]; lesson++) {
                for (MultipleChoiceQuestion q: new Lesson(context, unit, lesson).getPractice_questions()
                     ) {
                    addQuestion(q);
                }
            }
        }
    }

    private void addQuestion(MultipleChoiceQuestion question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_POS, question.getAnswer_pos());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    /* Method to return list of questions from DB query for a specified lesson, or gets all questions
    if the input unit_num is 0
     */
    public List<MultipleChoiceQuestion> getQuestions() {
        List<MultipleChoiceQuestion> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if(c.moveToFirst()) {
            do {
                MultipleChoiceQuestion question = new MultipleChoiceQuestion();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswer_pos(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_POS)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
