package com.emmahogan.gedcourse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.emmahogan.gedcourse.articles.list_articles
import com.emmahogan.gedcourse.instruction.LessonView

class MainActivity : AppCompatActivity() {

    lateinit var resources_button:Button
    lateinit var next_lesson_btn:Button
    var curr_unit:Int = 1
    var curr_lesson:Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        curr_unit = 1
        curr_lesson = 1

        resources_button = findViewById(R.id.resources_btn)
        next_lesson_btn = findViewById(R.id.next_lesson_btn)

        resources_button.setOnClickListener {
            startActivity(Intent(this@MainActivity, list_articles::class.java))
        }
        next_lesson_btn.setOnClickListener {
            val intent = Intent(this@MainActivity, LessonView::class.java)

            intent.putExtra("unit", curr_unit)
            intent.putExtra("lesson", curr_lesson)

            startActivity(intent)
        }
    }
}