package com.example.jsonkotlin

import android.view.View
import android.widget.ImageView
import android.widget.TextView

class ViewHolder(private val convertView: View?) {
    private var name: TextView? = null
    private var image: ImageView? = null
    private var brief: TextView? = null

    fun getName(): TextView? {
        if (name == null) {
            name = convertView!!.findViewById(R.id.txt_name) as TextView
        }
        return name
    }

    fun getImage(): ImageView? {
        if (image == null) {
            image = convertView!!.findViewById(R.id.img) as ImageView
        }
        return image
    }

    fun getBrief(): TextView? {
        if (brief == null) {
            brief = convertView!!.findViewById(R.id.txt_brief) as TextView
        }
        return brief
    }

}
