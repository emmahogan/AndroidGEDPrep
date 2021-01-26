package com.emmahogan.gedcourse

import android.content.res.Resources
import android.os.Bundle
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.exp

class SyllabusActivity : AppCompatActivity() {

    var units_list : MutableList<String> = ArrayList()
    val lessons_list : MutableList<MutableList<String>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_syllabus)

        initLessonData()

        var expandableListView = findViewById<ExpandableListView>(R.id.exp_listview)
        expandableListView.setAdapter(ExpandableListAdapter(this, units_list,lessons_list))
    }

    private fun initLessonData() {

        // Populate units list with unit titles from string array in resources
        units_list = resources.getStringArray(
            resources.getIdentifier(
                "unit_titles",
                "array",
                applicationContext.packageName
            )
        ).toMutableList()

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