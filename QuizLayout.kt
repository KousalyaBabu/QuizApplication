package com.example.onlinequizapplication.layout

import android.content.Context
import android.widget.*
import androidx.core.view.get
import androidx.core.view.iterator
import com.example.onlinequizapplication.data.MultiAnswer
import com.example.onlinequizapplication.data.Quiz
import com.example.onlinequizapplication.data.SingleAnswer
import com.example.onlinequizapplication.util.Common.makeTextBold

class QuizLayout(context: Context) : LinearLayout(context) {
    private val question = TextView(context)
    private val choiceView = RadioGroup(context)
    private var correctAnswer: Any? = null
    private var result = false
    private val resultView = TextView(context)

    init {
        setPadding(50,50,50,50)
        orientation = VERTICAL

        addView(question)
        addView(choiceView)
        val submitButton = Button(context)
        addView(submitButton)
        submitButton.text = "Submit"

        resultView.also {
            it.textSize = 15F
            it.makeTextBold()
        }
        addView(resultView)

        submitButton.setOnClickListener {
            choiceView.iterator().forEach {
                it.isEnabled=false
            }
            if (result)
                Toast.makeText(context,"Correct!",Toast.LENGTH_LONG).show()
            else{
                Toast.makeText(context,"Incorrect!",Toast.LENGTH_LONG).show()
                resultView.text = "The Correct answer is $correctAnswer"
            }
        }
    }

    fun setQuiz(quiz: Quiz) {
        question.text = quiz.question
        when (quiz.answerType) {
            is SingleAnswer -> {
                createSingleSelectChoices(quiz.options, quiz.answerType.correctAnswer)
                this.correctAnswer = quiz.answerType.correctAnswer
            }
            is MultiAnswer -> {
                createMultiSelectChoices(quiz.options, quiz.answerType.correctAnswers)
                this.correctAnswer = quiz.answerType.correctAnswers
            }
        }
    }

//    fun nextButtonListener(listener: OnClickListener)=nextButton.setOnClickListener(listener)

    //    fun choices(options:(choices:List<String>)->RadioGroup):RadioGroup{
//        return options(listOf())
//    }
    private fun createSingleSelectChoices(choices: List<String>, correctAnswer: String) {
        var selectedSingleChoiceAnswer = ""
        choiceView.apply {
            for (c in choices.indices)
                addView(RadioButton(context).also {
                    it.text = choices[c]
                    it.id = c
                })
            setOnCheckedChangeListener { group, checkedId ->
                selectedSingleChoiceAnswer = (group.get(checkedId) as RadioButton).text.toString()
                if(selectedSingleChoiceAnswer.isNotEmpty())
                result = correctAnswer == selectedSingleChoiceAnswer
            }
        }
    }

    private fun createMultiSelectChoices(choices: List<String>, correctAnswers: List<String>) {
        val selectedMultiChoiceAnswers = mutableListOf<String>()
        choiceView.apply {
            for (c in choices) {
                addView(CheckBox(context).also {
                    it.text = c
                    it.setOnCheckedChangeListener { buttonView, isChecked ->
                        if (isChecked) {
                            selectedMultiChoiceAnswers.add(buttonView.text.toString())
                        } else {
                            selectedMultiChoiceAnswers.remove(buttonView.text.toString())
                        }
                        result=correctAnswers == selectedMultiChoiceAnswers
                    }
                })
            }
        }
    }
}