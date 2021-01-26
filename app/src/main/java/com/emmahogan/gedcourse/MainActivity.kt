package com.emmahogan.gedcourse

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.emmahogan.gedcourse.articles.list_articles
import com.emmahogan.gedcourse.instruction.LessonView

class MainActivity : AppCompatActivity() {

    // Used to access shared preferences for highscore
    val SHARED_PREFS: String = "sharedPrefs"
    lateinit var prefs: SharedPreferences

    // Keys for current unit and lesson numbers saved in shared prefs
    val KEY_CURR_UNIT: String = "KEY_CURR_UNIT"
    val KEY_CURR_LESSON: String = "KEY_CURR_LESSON"

    lateinit var resources_button:Button
    lateinit var next_lesson_btn:Button
    lateinit var syllabus_btn:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)

        // Get current unit and lesson numbers from shared preferences, or default 1.1
        var unit_num = prefs.getInt(KEY_CURR_UNIT, 1)
        var lesson_num = prefs.getInt(KEY_CURR_LESSON, 1)

        resources_button = findViewById(R.id.resources_btn)
        next_lesson_btn = findViewById(R.id.next_lesson_btn)
        syllabus_btn = findViewById(R.id.syllabus_btn)

        // Include current lesson on Next Lesson Button label
        var nextLessonString: String = "Next Lesson: Section $unit_num.$lesson_num"
        next_lesson_btn.text = nextLessonString


        resources_button.setOnClickListener {
            startActivity(Intent(this@MainActivity, list_articles::class.java))
        }

        next_lesson_btn.setOnClickListener {
            startActivity(Intent(this@MainActivity, LessonView::class.java))
        }

        syllabus_btn.setOnClickListener {
            startActivity(Intent(this@MainActivity, SyllabusActivity::class.java))
        }
    }
}