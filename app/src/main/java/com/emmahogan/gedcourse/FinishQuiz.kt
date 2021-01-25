package com.emmahogan.gedcourse

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FinishQuiz : AppCompatActivity() {

    lateinit var lesson: Lesson
    lateinit var next_btn: Button

    var highscore: Int = 0

    // Variables needed for saving score in shared preferences
    val SHARED_PREFS: String = "sharedPrefs"
    lateinit var KEY_HIGHSCORE: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_quiz)

        var unit_num = intent.getIntExtra("unit", 1)
        var lesson_num = intent.getIntExtra("lesson", 1)

        lesson = Lesson(applicationContext, unit_num, lesson_num)

        // Get final score of quiz from intent extra
        var score: Int = intent.getIntExtra("score", 0)
        var total: Int = intent.getIntExtra("total", 0)

        // Call external method to save high score in shared preferences
        saveHighScore(score);

        next_btn = findViewById(R.id.finish_quiz_next_lesson_btn)

        next_btn.setOnClickListener {
            // Check if last lesson of last unit, if so go back to home
            if (unit_num == 11 && lesson_num == 7) {
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

    private fun saveHighScore(currScore: Int) {
        // Set name for highscore key in shared preferences with lesson and unit nums
        KEY_HIGHSCORE = "KEY_HIGHSCORE_" + lesson.unit_num + "_" + lesson.lesson_num

        var prefs: SharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)

        // Fetch the current highscore
        var currHigh = prefs.getInt(KEY_HIGHSCORE, 0)

        // If current score is greater than current high score, save in shared preferences
        if (currScore > currHigh) {
            var editor: SharedPreferences.Editor = prefs.edit()
            editor.putInt(KEY_HIGHSCORE, currScore)
            editor.apply()
        }
    }
}