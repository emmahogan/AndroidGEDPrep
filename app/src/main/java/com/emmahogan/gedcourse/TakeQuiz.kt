package com.emmahogan.gedcourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class TakeQuiz : AppCompatActivity() {

    lateinit var question_textview: TextView
    lateinit var score_textview: TextView
    lateinit var count_textview: TextView
    lateinit var rb_group: RadioGroup
    lateinit var rb_1: RadioButton
    lateinit var rb_2: RadioButton
    lateinit var rb_3: RadioButton
    lateinit var submit_btn: Button

    lateinit var dbHelper: QuizDBHelper
    lateinit var questionsList: List<MultipleChoiceQuestion>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_quiz)

        question_textview = findViewById(R.id.quiz_question)
        score_textview = findViewById(R.id.quiz_score)
        count_textview = findViewById(R.id.quiz_question_count)
        rb_group = findViewById(R.id.radio_group)
        rb_1 = findViewById(R.id.radio_button1)
        rb_2 = findViewById(R.id.radio_button2)
        rb_3 = findViewById(R.id.radio_button3)
        submit_btn = findViewById(R.id.submit_answer_btn)

        dbHelper = QuizDBHelper(applicationContext)
        questionsList = dbHelper.questions
    }
}