package com.example.jsonkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.widget.AdapterView



class MainActivity : AppCompatActivity() {

    private val BASE_URL = "https://gist.githubusercontent.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        athletes_list.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->

            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("ATHLETE", athletes_list.getItemAtPosition(position) as Athlete)
            intent.putExtras(bundle)
            startActivity(intent)
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val response = retrofit.create(Response::class.java)
        val call = response.athletes
        call.enqueue(object : Callback<AthletesList> {
            override fun onResponse(call: Call<AthletesList>, response: retrofit2.Response<AthletesList>) {
                val list = response.body()!!.athletesList
                val athletes = arrayOfNulls<Athlete>(list!!.size)
                for (i in list.indices) {
                    athletes[i] = list[i]
                }
                val rows = athletes_list
                rows.adapter = AthletesAdapter(this@MainActivity, athletes)
            }

            override fun onFailure(call: Call<AthletesList>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
