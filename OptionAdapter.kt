package com.example.onlinequizapplication.layout

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class OptionAdapter(private val context: Context,private val options:List<String>):BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return TextView(context).also {
            it.text=getItem(position)
        }
    }

    override fun getItem(position: Int): String=options.get(position)

    override fun getItemId(position: Int): Long =position.toLong()

    override fun getCount(): Int =options.size
}