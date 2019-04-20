package com.example.jsonkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.jsonkotlin.R.id.*
import kotlinx.android.synthetic.main.athlete.view.*


class AthletesAdapter internal constructor( internal var context: Context, internal var rows: Array<Athlete?>) : ArrayAdapter<Athlete>(context, R.layout.athlete, R.id.txt_name, rows) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        txt_name
        txt_brief
        img
        var rowView = convertView
        val viewHolder: ViewHolder
        if (rowView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            rowView = inflater.inflate(R.layout.athlete, parent, false)
            viewHolder = ViewHolder(rowView)
            rowView.tag = viewHolder
        } else {
            viewHolder = rowView.tag as ViewHolder
        }
        viewHolder.getName()!!.text = rows[position]!!.name
        if (rows[position]!!.image != "") {
            rowView!!.img.visibility = View.VISIBLE
            Glide.with(context)
                    .load(rows[position]!!.image.replace("http", "https"))
                    .into(rowView.img as ImageView)
        } else {
            rowView!!.img.visibility = View.GONE
        }
        viewHolder.getBrief()!!.text = rows[position]!!.brief.substring(0, 150).plus("...")

        return rowView
    }
}