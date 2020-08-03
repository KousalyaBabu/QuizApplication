package com.example.onlinequizapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinequizapplication.layout.AddQuizLayout

class AddQuizActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(AddQuizLayout(this))
    }
}