package com.emmahogan.gedcourse.quiz

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.emmahogan.gedcourse.instruction.Lesson
import com.emmahogan.gedcourse.R

class QuizActivity : AppCompatActivity() {

    lateinit var lesson: Lesson

    // Holds current high score for this lesson's quiz
    var curr_highscore: Int = 0

    // Used to access shared preferences for highscore
    val SHARED_PREFS: String = "sharedPrefs"
    val prefs: SharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)

    // Keys for current unit and lesson numbers saved in shared prefs
    val KEY_CURR_UNIT: String = "KEY_CURR_UNIT"
    val KEY_CURR_LESSON: String = "KEY_CURR_LESSON"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // Get current unit and lesson numbers from shared preferences, or default 1.1
        var unit_num = prefs.getInt(KEY_CURR_UNIT, 1)
        var lesson_num = prefs.getInt(KEY_CURR_LESSON, 1)

        // Set current lesson
        lesson = Lesson(
            applicationContext,
            unit_num,
            lesson_num
        )

        // var questions_arr = resources.getStringArray(resources.getIdentifier("questions_" + unit_num, "array", packageName))
        // var answers_arr = resources.getStringArray(resources.getIdentifier("answers_" + unit_num, "array", packageName))

        // Set text above start quiz button to show title of current lesson
        val topics_textview:TextView = findViewById(R.id.quiz_topics)
        topics_textview.text = "Press start to begin the quiz on Section " + lesson.unit_num + "." + lesson.lesson_num + ", " + lesson.title

        // Call external method to get current high score from shared preferences
        curr_highscore = getHighScore()

        // Display current high score in textview
        val highscore_textview:TextView = findViewById(R.id.highscore_textview)
        highscore_textview.text = "Current High Score: "
        val highscore_val:TextView = findViewById(R.id.highscore_val_textview)
        highscore_val.text = curr_highscore.toString() + "%"

        // Start quiz activity when start button is clicked
        val start_quiz_btn:Button = findViewById(R.id.start_quiz_btn)
        start_quiz_btn.setOnClickListener {
            startActivity(Intent(this@QuizActivity, TakeQuiz::class.java))
        }
    }

    private fun getHighScore(): Int {
        // Set name for highscore key in shared preferences with lesson and unit nums
        val KEY_HIGHSCORE: String = "KEY_HIGHSCORE_" + lesson.unit_num + "_" + lesson.lesson_num

        // Return the current high score from shared preferences, or the default value of 0
        return prefs.getInt(KEY_HIGHSCORE, 0)

    }

}