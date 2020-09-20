package com.example.carsensorapp.ui.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.carsensorapp.R
import com.example.carsensorapp.services.models.PrefModel

class PrefSpinnerAdapter(
    private val inflater: LayoutInflater,
    private val prefs: List<PrefModel>
) : BaseAdapter() {

    class ViewHolder (
        val textView: TextView
    ) {
        fun bindItem(pref: PrefModel) {
            textView.text = pref.name
        }
    }

    override fun getCount(): Int {
        return prefs.count()
    }

    override fun getItem(position: Int): Any {
        return prefs[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val pref = prefs[position]
        if (convertView == null) {
            val view = inflater.inflate(R.layout.spinner_layout, parent, false)
            val textView = view.findViewById<TextView>(R.id.spinner_layout__text_view)
            val viewHolder = ViewHolder(textView);
            view.tag = viewHolder
            viewHolder.bindItem(pref)

            return view
        }

        val viewHolder = convertView.tag as ViewHolder
        viewHolder.bindItem(pref)

        return convertView
    }
}