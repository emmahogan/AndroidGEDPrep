package com.emmahogan.gedcourse

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class QuizActivity : AppCompatActivity() {

    lateinit var lesson: Lesson


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        var unit_num = intent.getIntExtra("unit", 1)
        var lesson_num = intent.getIntExtra("lesson", 1)

        var questions_arr = resources.getStringArray(resources.getIdentifier("questions_" + unit_num, "array", packageName))
        var answers_arr = resources.getStringArray(resources.getIdentifier("answers_" + unit_num, "array", packageName))

        lesson = Lesson(applicationContext,unit_num,lesson_num)



        val start_quiz_btn:Button = findViewById(R.id.start_quiz_btn)
        start_quiz_btn.setOnClickListener {
            startQuiz()
        }
    }

    private fun startQuiz() {
        val intent = Intent(this@QuizActivity, TakeQuiz::class.java)
        intent.putExtra("unit", lesson.unit_num)
        intent.putExtra("lesson", lesson.lesson_num)

        startActivity(intent)
    }
}