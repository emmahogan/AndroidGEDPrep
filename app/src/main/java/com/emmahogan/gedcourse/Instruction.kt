package com.emmahogan.gedcourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Instruction : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction)

        showSubtopics()
    }

    fun showSubtopics() {
        val topics = ArrayList<Subtopic>()
    }
}