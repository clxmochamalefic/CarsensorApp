package com.example.carsensorapp.ui.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.carsensorapp.R
import com.example.carsensorapp.services.models.CodeNamePair

class ColorSpinnerAdapter(
    private val inflater: LayoutInflater,
    private val colors: List<CodeNamePair>
) : BaseAdapter() {

    class ViewHolder (
        val textView: TextView
    ) {
        fun bindItem(color: CodeNamePair) {
            textView.text = color.name
        }
    }

    override fun getCount(): Int {
        return colors.count()
    }

    override fun getItem(position: Int): Any {
        return colors[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val color = colors[position]
        if (convertView == null) {
            val view = inflater.inflate(R.layout.spinner_layout, parent, false)
            val textView = view.findViewById<TextView>(R.id.spinner_layout__text_view)
            val viewHolder = ViewHolder(textView);
            view.tag = viewHolder
            viewHolder.bindItem(color)

            return view
        }

        val viewHolder = convertView.tag as ViewHolder
        viewHolder.bindItem(color)

        return convertView
    }
}