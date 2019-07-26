package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var foodlist = arrayListOf(
        Food("lil", "lilpump"),
        Food("quentin", "tarantino")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mAdapter = foodrvadapter(this, foodlist)
        recyclerview.adapter = mAdapter

        val lm = LinearLayoutManager(this)
        recyclerview.layoutManager = lm
        recyclerview.setHasFixedSize(true)
    }
}
