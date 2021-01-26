package com.emmahogan.gedcourse.quiz

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.emmahogan.gedcourse.MainActivity
import com.emmahogan.gedcourse.instruction.CourseComplete
import com.emmahogan.gedcourse.instruction.Lesson
import com.emmahogan.gedcourse.instruction.LessonView
import com.emmahogan.gedcourse.R

class FinishQuiz : AppCompatActivity() {

    lateinit var lesson: Lesson
    var unit_num: Int = 1
    var lesson_num: Int = 1

    lateinit var next_btn: Button
    lateinit var try_again_btn: Button
    lateinit var review_lesson_btn: Button
    lateinit var home_btn: Button

    // Used to access shared preferences for highscore
    val SHARED_PREFS: String = "sharedPrefs"
    lateinit var prefs: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    // Keys saved in shared prefs
    val KEY_CURR_UNIT: String = "KEY_CURR_UNIT"
    val KEY_CURR_LESSON: String = "KEY_CURR_LESSON"
    lateinit var KEY_HIGHSCORE: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_quiz)

        // Instantiate SharedPreferences object
        prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)

        // Used to edit shared prefs
        editor = prefs.edit()

        // Get current unit and lesson numbers from shared preferences, or default 1.1
        unit_num = prefs.getInt(KEY_CURR_UNIT, 1)
        lesson_num = prefs.getInt(KEY_CURR_LESSON, 1)

        // Set current lesson
        lesson = Lesson(
            applicationContext,
            unit_num,
            lesson_num
        )

        // Get final score of quiz from intent extra
        val numCorrect: Int = intent.getIntExtra("score", 0)
        val total: Int = intent.getIntExtra("total", 0)

        // Divide numCorrect by total and multiply by 100 to get score
        val score:Int = (numCorrect * 100)/total

        var score_textview = findViewById<TextView>(R.id.quiz_score_textview)
        score_textview.text = score.toString() + "%"

        // Call external method to save high score in shared preferences
        saveHighScore(score);

        next_btn = findViewById(R.id.finish_quiz_next_lesson_btn)
        try_again_btn = findViewById(R.id.finish_quiz_try_again_btn)
        review_lesson_btn = findViewById(R.id.finish_quiz_review_btn)
        home_btn = findViewById(R.id.finish_quiz_home_btn)

        initNavBtns()


        }




    private fun initNavBtns() {
        next_btn.setOnClickListener {
            nextLesson()
        }
        try_again_btn.setOnClickListener {
            tryAgain()
        }
        review_lesson_btn.setOnClickListener {
            reviewCurrLesson()
        }
        home_btn.setOnClickListener {
            home()
        }
    }

    private fun home() {
        // Call external method to increment current lesson in Shared Prefs
        incLesson()
        // Go to home page
        startActivity(Intent(this@FinishQuiz, MainActivity::class.java))
    }

    /* Method to review content of current lesson when btn is clicked */
    private fun reviewCurrLesson() {
        startActivity(Intent(this@FinishQuiz, LessonView::class.java))
    }

    /* Method to start quiz for this lesson again when try again btn is clicked */
    private fun tryAgain() {
        startActivity(Intent(this@FinishQuiz, QuizActivity::class.java))
    }

    /* Method to start next lesson when next lesson btn is clicked */
    private fun nextLesson() {
        // Call external method to increment current lesson in Shared Prefs
        incLesson()
        // Go to next lesson
        startActivity(Intent(this@FinishQuiz, LessonView::class.java))
    }

    /* Method to increment current lesson and save as new current lesson in Shared Prefs */
    private fun incLesson() {
        // Check if last lesson of last unit, if so go back to home
        if (unit_num == 11 && lesson_num == 7) {
            startActivity(Intent(this@FinishQuiz, CourseComplete::class.java))
        } else {

            // Check if last lesson in unit
            if (lesson_num == lesson.num_lessons_in_unit) {

                // Increment unit and set lesson number to one
                unit_num++
                lesson_num = 1

            } else {
                // Just increment lesson number
                lesson_num++
            }

            // Save new current lesson in shared preferences
            editor.putInt(KEY_CURR_LESSON, lesson_num)
            editor.putInt(KEY_CURR_UNIT, unit_num)
            editor.apply()

        }
    }

    private fun saveHighScore(currScore: Int) {
        // Set name for highscore key in shared preferences with lesson and unit nums
        KEY_HIGHSCORE = "KEY_HIGHSCORE_" + lesson.unit_num + "_" + lesson.lesson_num

        // Fetch the current highscore
        var currHigh = prefs.getInt(KEY_HIGHSCORE, 0)

        // If current score is greater than current high score, save in shared preferences
        if (currScore > currHigh) {
            editor.putInt(KEY_HIGHSCORE, currScore)
            editor.apply()
        }
    }
}