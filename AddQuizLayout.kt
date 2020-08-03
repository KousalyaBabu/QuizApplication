package com.example.onlinequizapplication.layout

import android.content.Context
import android.view.View
import android.widget.*
import com.example.onlinequizapplication.data.MultiAnswer
import com.example.onlinequizapplication.data.Quiz
import com.example.onlinequizapplication.data.SingleAnswer
import com.example.onlinequizapplication.util.Common.makeTextBold

class AddQuizLayout(context: Context) : LinearLayout(context) {
    companion object {
        const val SINGLE = 1
        const val MULTIPLE = 2
    }

    private var questionNumber = 1
    private val questionView = EditText(context)
    private val optionOneView = EditText(context)
    private val optionTwoView = EditText(context)
    private val optionThreeView = EditText(context)
    private val optionFourView = EditText(context)
    private val optionType = RadioGroup(context)
    private var answers = mutableListOf<String>()
    private val topicView=EditText(context)
    init {
        setPadding(50, 50, 50, 50)
        orientation = VERTICAL
        topicView.hint="Enter topic of this quiz"
        addView(topicView)

        addView(getEditText("Enter question", questionView))
        addView(getEditText("Enter option A", optionOneView))
        addView(getEditText("Enter option B", optionTwoView))
        addView(getEditText("Enter option C", optionThreeView))
        addView(getEditText("Enter option D", optionFourView))
        val optionTypeRadioView = LinearLayout(context)
        addView(optionTypeRadioView)

        optionTypeRadioView.addView(optionType)

        val singleAnswerView = EditText(context)
        singleAnswerView.visibility=View.GONE
        addView(getEditText("Enter correct answer", singleAnswerView))

        val multipleAnswersView = LinearLayout(context).also {
            it.visibility=View.GONE
        }
        addView(multipleAnswersView)

        optionType.apply {
            addView(RadioButton(context).also {
                it.text = "Single answer"
                it.id = SINGLE
            })
            addView(RadioButton(context).also {
                it.text = "Multiple answer"
                it.id = MULTIPLE
            })
        }
        optionType.setOnCheckedChangeListener { group: RadioGroup?, checkedId: Int ->
            if (checkedId == SINGLE) {
                singleAnswerView.visibility=View.VISIBLE
                multipleAnswersView.visibility = View.GONE
            } else {
                multipleAnswersView.addView(getAnswers(optionOneView.text.toString()))
                multipleAnswersView.addView(getAnswers(optionTwoView.text.toString()))
                multipleAnswersView.addView(getAnswers(optionThreeView.text.toString()))
                multipleAnswersView.addView(getAnswers(optionFourView.text.toString()))
                singleAnswerView.visibility = View.GONE
                multipleAnswersView.visibility = View.VISIBLE
            }
        }
        val addQuizButton = Button(context)
        addView(addQuizButton)
        addQuizButton.text = "Add"
        addQuizButton.setOnClickListener {
            val question = questionView.text.toString()
            val choices = listOf(
                optionOneView.text.toString(),
                optionTwoView.text.toString(),
                optionThreeView.text.toString(),
                optionFourView.text.toString()
            )
            val answer = singleAnswerView.text.toString()
            if(optionType.checkedRadioButtonId== SINGLE)
                QuizQuestions.allQuiz.put(topicView.text.toString(), listOf(Quiz(questionNumber,question, choices, SingleAnswer(answer))))
            else
                QuizQuestions.allQuiz.put(topicView.text.toString(),listOf(Quiz(questionNumber,question, choices, MultiAnswer(answers))))
            questionView.text.clear()
            optionOneView.text.clear()
            optionTwoView.text.clear()
            optionThreeView.text.clear()
            optionFourView.text.clear()
            singleAnswerView.text.clear()
            answers.clear()
            questionNumber++
        }
    }

    private fun getEditText(label: String, view: EditText): EditText {
        view.hint = label
        view.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        return view
    }

    private fun getAnswers(label: String) = CheckBox(context).apply {
        text = label
        setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) answers.add(label) else answers.remove(label)
        }
    }
}