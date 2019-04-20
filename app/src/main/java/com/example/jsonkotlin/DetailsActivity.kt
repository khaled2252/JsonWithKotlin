package com.example.jsonkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        back
        details_img
        details_brief
        details_name
        setContentView(R.layout.activity_details)
        val athlete = intent.extras!!.getSerializable("ATHLETE") as Athlete
        details_name.text = athlete.name
        details_brief.text = athlete.brief
        if (athlete.image != "") {
            Glide.with(this)
                    .load(athlete.image.replace("http", "https"))
                    .into(details_img as ImageView)
        } else {
            details_img.visibility = View.GONE
        }
        back.setOnClickListener {
            finish()
        }
    }
}
