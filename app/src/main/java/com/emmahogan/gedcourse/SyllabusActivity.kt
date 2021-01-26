package com.emmahogan.gedcourse

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.emmahogan.gedcourse.instruction.LessonView
import kotlin.math.exp

class SyllabusActivity : AppCompatActivity() {

    // Hold data for syllabus
    var units_list : MutableList<String> = ArrayList()
    val lessons_list : MutableList<MutableList<String>> = ArrayList()

    // Used to access shared preferences for highscore
    val SHARED_PREFS: String = "sharedPrefs"

    // Keys saved in shared prefs
    val KEY_CURR_UNIT: String = "KEY_CURR_UNIT"
    val KEY_CURR_LESSON: String = "KEY_CURR_LESSON"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_syllabus)

        val prefs: SharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)

        // Used to edit shared prefs
        val editor: SharedPreferences.Editor = prefs.edit()

        initLessonData()

        var expandableListView = findViewById<ExpandableListView>(R.id.exp_listview)
        expandableListView.setAdapter(ExpandableListAdapter(this, units_list,lessons_list))

        // Set child click listener to save that lesson as current in shared prefs and start that lesson activity
        expandableListView!!.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            // Save lesson and unit numbers in shared prefs
            editor.putInt(KEY_CURR_UNIT, groupPosition + 1)
            editor.putInt(KEY_CURR_LESSON, childPosition + 1)
            editor.apply()

            // Start current lesson activity
            startActivity(Intent(this@SyllabusActivity, LessonView::class.java))
            false
        }
    }

    private fun initLessonData() {

        // Populate units list with unit titles from string array in resources
        var unitResArr = resources.getStringArray(
            resources.getIdentifier(
                "unit_titles",
                "array",
                applicationContext.packageName
            )
        )

        // Add "Chapter X: " to the beginning of each
        for(index in 1..unitResArr.size) {
            units_list.add("Chapter $index: ${unitResArr[index-1]}")
        }


        // For each unit, populate each mutable list in the lessons list with its subtopic names from resources
        for(index in 1..units_list.size) {
            lessons_list.add(resources.getStringArray(
                resources.getIdentifier(
                    "subtopic_names_$index",
                    "array",
                    applicationContext.packageName
                )
            ).toMutableList())
        }
    }
}