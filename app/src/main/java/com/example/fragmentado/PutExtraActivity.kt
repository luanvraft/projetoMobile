package com.example.fragmentado

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class PutExtraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_put_extra)

        val imageView: ImageView = findViewById(R.id.Gif)
        Glide.with(this).load(R.drawable.liebe).into(imageView)

        val message = intent.getStringExtra("texto")
        val reciverTxt: TextView = findViewById(R.id.txt_putRecive)
        reciverTxt.text = message
    }
}