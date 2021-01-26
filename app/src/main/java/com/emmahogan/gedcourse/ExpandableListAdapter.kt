package com.emmahogan.gedcourse

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

class ExpandableListAdapter(var context: Context, var units : MutableList<String>, var lessons : MutableList<MutableList<String>>) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return units.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return lessons[groupPosition].size
    }

    override fun getGroup(groupPosition: Int): String {
        return units[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): String {
        return lessons[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var groupView = convertView
        if(groupView == null) {
            groupView = LayoutInflater.from(context).inflate(R.layout.layout_unit_group, parent, false)
        }

        val unit_title = groupView?.findViewById<TextView>(R.id.unit_header)
        unit_title?.text = getGroup(groupPosition)
        return groupView!!
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertView = convertView
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_syll_lesson, parent, false)
        }

        val lesson_header = convertView?.findViewById<TextView>(R.id.lesson_header)
        lesson_header?.text = getChild(groupPosition,childPosition)
        return convertView!!
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

}