package com.example.onlinequizapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinequizapplication.layout.QuizFragment
import com.example.onlinequizapplication.layout.QuizListFragment

class QuizActivity:AppCompatActivity() {
    companion object{
        const val VIEW_ID=11
        var questionCount=0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view= FrameLayout(this)
        setContentView(view)
        view.id= VIEW_ID
        supportFragmentManager.beginTransaction().add(view.id, QuizListFragment()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("Next")?.apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        questionCount++
        supportFragmentManager.beginTransaction().replace(VIEW_ID, QuizFragment()).commit()
        return super.onOptionsItemSelected(item)
    }
}