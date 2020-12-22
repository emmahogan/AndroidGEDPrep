package com.emmahogan.gedcourse

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView

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
        var q_arr = r.getStringArray(R.array.questions_1_1)
        var a_arr = r.getStringArray(R.array.answers_1_1)

        for(i in 0..11) {
            questions.add(Question(q_arr[i], a_arr[i], false))
        }

        var title:String = r.getString(R.string.title_1_1)

        lesson = Lesson(title, 1, 1, questions)

    }

    fun showSubtopics() {
        val topics = ArrayList<Subtopic>()
        for (i in 0..11) {
            topics.add(Subtopic(lesson.title, lesson.practice_questions[i].question))
        }

        val adapter = InstructionAdapter(this@Instruction, topics)

        val list = findViewById<ListView>(R.id.subtopics)

        list.adapter = adapter
    }
}