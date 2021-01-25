package com.emmahogan.gedcourse

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class QuizActivity : AppCompatActivity() {

    lateinit var lesson: Lesson

    // Holds current high score for this lesson's quiz
    var curr_highscore: Int = 0

    // Used to access shared preferences for highscore
    val SHARED_PREFS: String = "sharedPrefs"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        var unit_num = intent.getIntExtra("unit", 1)
        var lesson_num = intent.getIntExtra("lesson", 1)

        var questions_arr = resources.getStringArray(resources.getIdentifier("questions_" + unit_num, "array", packageName))
        var answers_arr = resources.getStringArray(resources.getIdentifier("answers_" + unit_num, "array", packageName))

        lesson = Lesson(applicationContext,unit_num,lesson_num)

        // Set text above start quiz button to show title of current lesson
        val topics_textview:TextView = findViewById(R.id.quiz_topics)
        topics_textview.text = "Press start to begin the quiz on Section " + lesson.unit_num + "." + lesson.lesson_num + ", " + lesson.title

        // Call external method to get current high score from shared preferences
        curr_highscore = getHighScore()

        // Display current high score in textview
        val highscore_textview:TextView = findViewById(R.id.highscore_textview)
        highscore_textview.text = "Current High Score: " + curr_highscore

        val start_quiz_btn:Button = findViewById(R.id.start_quiz_btn)
        start_quiz_btn.setOnClickListener {
            startQuiz()
        }
    }

    private fun getHighScore(): Int {
        // Set name for highscore key in shared preferences with lesson and unit nums
        val KEY_HIGHSCORE: String = "KEY_HIGHSCORE_" + lesson.unit_num + "_" + lesson.lesson_num

        var prefs: SharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)

        // Return the current high score from shared preferences, or the default value of 0
        return prefs.getInt(KEY_HIGHSCORE, 0)

    }

    private fun startQuiz() {
        val intent = Intent(this@QuizActivity, TakeQuiz::class.java)
        intent.putExtra("unit", lesson.unit_num)
        intent.putExtra("lesson", lesson.lesson_num)

        startActivity(intent)
    }
}