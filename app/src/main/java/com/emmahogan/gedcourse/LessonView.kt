package com.emmahogan.gedcourse

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.VideoView
import androidx.core.view.isVisible

class LessonView : AppCompatActivity() {

    lateinit var lesson: Lesson
    lateinit var vid_uri: Uri

    // Variables needed for using shared preferences
    val SHARED_PREFS: String = "sharedPrefs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_view)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        var unit_num = intent.getIntExtra("unit", 1)
        var lesson_num = intent.getIntExtra("lesson", 1)

        lesson = Lesson(applicationContext,unit_num,lesson_num)

        val lessonview_title = findViewById<TextView>(R.id.lessonview_title)

        val lessonview_content = findViewById<TextView>(R.id.lessonview_content)
        val lessonview_example = findViewById<TextView>(R.id.lessonview_example)
        val lessonview_citation = findViewById<TextView>(R.id.lessonview_citation)
        val next_btn = findViewById<Button>(R.id.next_btn)

        lessonview_title.text = unit_num.toString() + "." + lesson_num.toString() + " " + lesson.title
        lessonview_content.text = lesson.content
        lessonview_example.text = lesson.example
        lessonview_citation.text = lesson.citation

        // Call external method to initialize animation button if animation exists for lesson
        initAnimBtn()

        next_btn.setOnClickListener {

            val intent = Intent(this@LessonView, QuizActivity::class.java)
            intent.putExtra("unit", unit_num)
            intent.putExtra("lesson", lesson_num)

            startActivity(intent)

        }
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