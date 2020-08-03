package com.example.onlinequizapplication.layout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.onlinequizapplication.QuizActivity

class QuizFragment:Fragment() {
    companion object{
        const val SELECTED_TOPIC_KEY="com.example.onlinequizapplication.layout.selectedTopic"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val value=arguments
        val topic=value?.get(SELECTED_TOPIC_KEY) as String
        val layout=QuizLayout(requireContext())
        val quiz=QuizQuestions.allQuiz[topic]
        quiz?.get(QuizActivity.questionCount)?.let {
            layout.setQuiz(it)
        }
        return layout
    }
}