package com.example.onlinequizapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view=LinearLayout(this)
        setContentView(view)
        view.orientation=LinearLayout.VERTICAL
        val takeQuizButton=Button(this)
        view.addView(takeQuizButton)
        takeQuizButton.text="Take Quiz"
        takeQuizButton.setBackgroundColor(Color.GREEN)
        val addQuizButton=Button(this)
        view.addView(addQuizButton)
        addQuizButton.text="Add Quiz"
        addQuizButton.setBackgroundColor(Color.RED)

        takeQuizButton.setOnClickListener {
            startActivity(Intent(this@MainActivity,QuizActivity::class.java))
        }
        addQuizButton.setOnClickListener {
            startActivity(Intent(this@MainActivity,AddQuizActivity::class.java))
        }
    }
}