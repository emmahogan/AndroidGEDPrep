package com.emmahogan.gedcourse

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.AdapterView
import android.widget.ListView
import androidx.core.text.HtmlCompat

class Instruction : AppCompatActivity() {

    lateinit var lesson:Lesson
    lateinit var context:Context
    lateinit var r:Resources

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction)

        context = this.applicationContext
        r = context.getResources()

        initLessonData()

        showSubtopics()
    }

    fun initLessonData() {

        var questions = ArrayList<Question>()
        var subtitle_arr = r.getStringArray(R.array.subtopic_names_1)
        var q_arr = r.getStringArray(R.array.questions_1)
        var a_arr = r.getStringArray(R.array.answers_1)

        for(i in 0..11) {
            questions.add(Question(q_arr[i], a_arr[i], false))
        }

        var title:String = subtitle_arr[0]


    }

    fun showSubtopics() {
        val topics = ArrayList<Subtopic>()
        var subtopic_titles = r.getStringArray(R.array.subtopic_names_1)
        var subtopic_contents = r.getStringArray(R.array.subtopic_content_1)
        var subtopic_examples = r.getStringArray(R.array.examples_1)
        var subtopic_citations = r.getStringArray(R.array.citations_1)
        for (i in 0..(subtopic_titles.size - 1)) {
            topics.add(Subtopic(subtopic_titles[i], subtopic_contents[i], subtopic_examples[i], subtopic_citations[i]))
        }

        val adapter = InstructionAdapter(this@Instruction, topics)

        val list = findViewById<ListView>(R.id.subtopics)

        list.adapter = adapter
    }
}