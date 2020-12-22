package com.emmahogan.gedcourse

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class InstructionAdapter (context: Context, subtopics:ArrayList<Subtopic>) : ArrayAdapter<Subtopic>(context, 0, subtopics) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var itemView: View?

        itemView = LayoutInflater.from(context).inflate(R.layout.subtopic, parent, false)

        val subtopic_title = itemView.findViewById<TextView>(R.id.subtopic_title)
        val subtopic_content = itemView.findViewById<TextView>(R.id.subtopic_content)

        val itemData = getItem(position)

        subtopic_title.text = itemData?.subtopic_title
        subtopic_content.text = itemData?.subtopic_content

        return itemView
    }
}