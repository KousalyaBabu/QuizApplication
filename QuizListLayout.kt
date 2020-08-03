package com.example.onlinequizapplication.layout

import android.content.Context
import android.widget.ListView

class QuizListLayout(context: Context):ListView(context) {
    init {
        adapter=OptionAdapter(context,QuizQuestions.allQuiz.keys.toList())
    }
}