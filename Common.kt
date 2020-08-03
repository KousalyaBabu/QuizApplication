package com.example.onlinequizapplication.util

import android.graphics.Typeface
import android.widget.TextView

object Common {
    fun TextView.makeTextBold(){
        this.typeface= Typeface.DEFAULT_BOLD
    }
}