package com.example.onlinequizapplication.layout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.onlinequizapplication.QuizActivity

class QuizListFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return QuizListLayout(requireContext()).apply {
            setOnItemClickListener { parent, view, position, id ->
                val bundle=Bundle()
                bundle.putString(QuizFragment.SELECTED_TOPIC_KEY, (view as TextView).text.toString())
                val quizFragment=QuizFragment()
                requireFragmentManager().beginTransaction().replace(QuizActivity.VIEW_ID,quizFragment).commit()
                quizFragment.arguments=bundle
            }
        }
    }
}