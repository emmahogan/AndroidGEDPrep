package com.emmahogan.gedcourse.instruction

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.VideoView
import com.emmahogan.gedcourse.R
import com.emmahogan.gedcourse.instruction.Lesson
import com.emmahogan.gedcourse.quiz.QuizActivity

class LessonView : AppCompatActivity() {

    lateinit var lesson: Lesson
    lateinit var vid_uri: Uri

    // Variables needed for using shared preferences
    val SHARED_PREFS: String = "sharedPrefs"

    // Keys for current unit and lesson numbers saved in shared prefs
    val KEY_CURR_UNIT: String = "KEY_CURR_UNIT"
    val KEY_CURR_LESSON: String = "KEY_CURR_LESSON"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_view)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        // Initialize shared preferences
        var prefs: SharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)

        // Get current unit and lesson numbers from shared preferences, or default 1.1
        var unit_num = prefs.getInt(KEY_CURR_UNIT, 1)
        var lesson_num = prefs.getInt(KEY_CURR_LESSON, 1)

        // Set current lesson
        lesson = Lesson(
            applicationContext,
            unit_num,
            lesson_num
        )

        // Call external method to initialize all lesson text in textviews
        initLessonText()

        // Call external method to initialize animation button if animation exists for lesson
        initAnimBtn()

        // Set next btn to direct user to quiz start page for current lesson
        val next_btn = findViewById<Button>(R.id.next_btn)

        next_btn.setOnClickListener {
            startActivity(Intent(this@LessonView, QuizActivity::class.java))
        }
    }

    /* Method to initialize all lesson text in textviews */

    private fun initLessonText() {
        // Fetch all textviews from layout
        val lessonview_title = findViewById<TextView>(R.id.lessonview_title)
        val lessonview_content = findViewById<TextView>(R.id.lessonview_content)
        val lessonview_example = findViewById<TextView>(R.id.lessonview_example)
        val lessonview_citation = findViewById<TextView>(R.id.lessonview_citation)

        // Initialize with the correct content for current lesson
        lessonview_title.text = lesson.unit_num.toString() + "." + lesson.lesson_num.toString() + " " + lesson.title
        lessonview_content.text = lesson.content
        lessonview_example.text = lesson.example
        lessonview_citation.text = lesson.citation
    }

    /* Method to initialize animation button if animation exists for lesson */
    private fun initAnimBtn() {

        // Fetch button
        val watchAnimBtn = findViewById<Button>(R.id.watch_anim_btn)

        // Get filename for resource using current lesson num and unit num
        val filename:String = "anim" + lesson.unit_num + "_" + lesson.lesson_num

        // If an animation video exists under this filename in resources, connect and display button
        if(resources.getIdentifier(filename, "raw", packageName) != 0) {
            watchAnimBtn.visibility = View.VISIBLE
            vid_uri = Uri.parse("android.resource://" + packageName + "/" + resources.getIdentifier(filename, "raw", packageName))
        } else {

            // If no animation video exists for this lesson, do not display button
            watchAnimBtn.visibility = View.GONE
        }
    }

    fun playAnimation(view: View) {
        val buttonClicked = findViewById<Button>(view.id)

        val alert = Dialog(this@LessonView)
        alert.setContentView(R.layout.lesson_animation)

        val playButton = alert.findViewById<Button>(R.id.play_btn)
        val videoPlayer = alert.findViewById<VideoView>(R.id.videoPlayer)
        val pauseBtn = alert.findViewById<Button>(R.id.pause_btn)
        val cancelBtn = alert.findViewById<Button>(R.id.cancel_btn)

        if(buttonClicked.id == R.id.watch_anim_btn) {
            videoPlayer.setVideoURI(vid_uri)
            alert.show()
        }

        playButton.setOnClickListener {
            videoPlayer.start()
        }

        pauseBtn.setOnClickListener {
            videoPlayer.pause()
        }

        cancelBtn.setOnClickListener {
            alert.dismiss()
        }
    }


}