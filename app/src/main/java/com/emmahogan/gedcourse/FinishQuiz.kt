package com.emmahogan.gedcourse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FinishQuiz : AppCompatActivity() {

    lateinit var lesson: Lesson
    lateinit var next_btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_quiz)

        var unit_num = intent.getIntExtra("unit", 1)
        var lesson_num = intent.getIntExtra("lesson", 1)

        lesson = Lesson(applicationContext,unit_num,lesson_num)

        next_btn = findViewById(R.id.finish_quiz_next_lesson_btn)

        next_btn.setOnClickListener {
            // Check if last lesson of last unit, if so go back to home
            if(unit_num == 11 && lesson_num == 7) {
                startActivity(Intent(this@FinishQuiz, MainActivity::class.java))
            } else {

                // Check if last lesson in unit
                if (lesson_num == lesson.num_lessons_in_unit) {
                    unit_num++
                    lesson_num = 1

                } else {
                    lesson_num++
                }

                val intent = Intent(this@FinishQuiz, LessonView::class.java)
                intent.putExtra("unit", unit_num)
                intent.putExtra("lesson", lesson_num)

                startActivity(intent)
            }
        }


    }
}