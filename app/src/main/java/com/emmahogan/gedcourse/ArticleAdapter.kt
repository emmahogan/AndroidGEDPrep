package com.emmahogan.gedcourse

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView

class ArticleAdapter (context:Context, articles:ArrayList<Article>) : ArrayAdapter<Article>(context, 0, articles) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var itemView:View?

        itemView = LayoutInflater.from(context).inflate(R.layout.resource_article, parent, false)

        val article_title = itemView.findViewById<TextView>(R.id.article_title)
        val subtitle = itemView.findViewById<TextView>(R.id.subtitle)
        val header_image = itemView.findViewById<ImageView>(R.id.header_image)

        val itemData = getItem(position)

        article_title.text = itemData?.title
        subtitle.text = itemData?.subtitle
        header_image.setImageResource(itemData?.header_image!!)

        return itemView
    }
}