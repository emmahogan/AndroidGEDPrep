package com.emmahogan.gedcourse.articles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.emmahogan.gedcourse.R

class read_article : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_article)

        val title = intent.getStringExtra("title")
        val subtitle = intent.getStringExtra("subtitle")
        val content = intent.getStringExtra("content")

        // Give default as skimask
        val header_image = intent.getIntExtra("image", R.drawable.study1)

        val title_readview = findViewById<TextView>(R.id.title_readview)
        val subtitle_readview = findViewById<TextView>(R.id.subtitle_readview)
        val content_readview = findViewById<TextView>(R.id.content_readview)
        val image_readview = findViewById<ImageView>(R.id.image_readview)

        title_readview.text = title
        subtitle_readview.text = subtitle
        content_readview.text = content
        image_readview.setImageResource(header_image)
    }
}