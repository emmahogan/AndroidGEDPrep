package com.emmahogan.gedcourse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var resources_button:Button
    lateinit var next_lesson_btn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resources_button = findViewById(R.id.resources_btn)
        next_lesson_btn = findViewById(R.id.next_lesson_btn)

        resources_button.setOnClickListener {
            startActivity(Intent(this@MainActivity, list_articles::class.java))
        }
        next_lesson_btn.setOnClickListener {
            startActivity(Intent(this@MainActivity, Instruction::class.java))
        }
    }
}