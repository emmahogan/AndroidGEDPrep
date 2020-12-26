package com.emmahogan.gedcourse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LessonView : AppCompatActivity() {

    lateinit var lesson: Lesson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_view)

        var unit_num = intent.getIntExtra("unit", 1)
        var lesson_num = intent.getIntExtra("lesson", 1)

        lesson = Lesson(applicationContext,unit_num,lesson_num)

        val lessonview_title = findViewById<TextView>(R.id.lessonview_title)
        val lessonview_content = findViewById<TextView>(R.id.lessonview_content)
        val lessonview_example = findViewById<TextView>(R.id.lessonview_example)
        val lessonview_citation = findViewById<TextView>(R.id.lessonview_citation)
        val next_btn = findViewById<Button>(R.id.next_btn)

        lessonview_title.text = lesson.title
        lessonview_content.text = lesson.content
        lessonview_example.text = lesson.example
        lessonview_citation.text = lesson.citation

        next_btn.setOnClickListener {

            // Check if last lesson of last unit, if so go back to home
            if(unit_num == 11 && lesson_num == 7) {
                startActivity(Intent(this@LessonView, MainActivity::class.java))
            } else {

                // Check if last lesson in unit
                if (lesson_num == lesson.num_lessons_in_unit) {
                    unit_num++
                    lesson_num = 1

                } else {
                    lesson_num++
                }

                val intent = Intent(this@LessonView, LessonView::class.java)
                intent.putExtra("unit", unit_num)
                intent.putExtra("lesson", lesson_num)

                startActivity(intent)
            }
        }
    }
}