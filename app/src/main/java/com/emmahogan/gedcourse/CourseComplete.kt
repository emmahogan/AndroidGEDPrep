package com.emmahogan.gedcourse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CourseComplete : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_complete)

        // Return home button takes user back to the home page by starting the Main Activity
        val return_home_btn: Button = findViewById(R.id.course_complete_home_btn)
        return_home_btn.setOnClickListener {
            startActivity(Intent(this@CourseComplete, MainActivity::class.java))
        }
    }
}