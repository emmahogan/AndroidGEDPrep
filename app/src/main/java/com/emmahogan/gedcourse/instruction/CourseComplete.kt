package com.emmahogan.gedcourse.instruction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.emmahogan.gedcourse.MainActivity
import com.emmahogan.gedcourse.R

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_nav, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_home -> { startActivity(Intent(this@CourseComplete, MainActivity::class.java))}
        }
        return super.onOptionsItemSelected(item)
    }
}