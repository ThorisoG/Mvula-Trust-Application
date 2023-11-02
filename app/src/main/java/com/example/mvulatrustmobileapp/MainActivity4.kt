package com.example.mvulatrustmobileapp

import android.os.Bundle
import android.view.View
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity

class MainActivity4 : AppCompatActivity() {
    var gridView: GridView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        gridView = findViewById<View>(R.id.grid_view) as GridView
        gridView!!.adapter = ImageAdapter(this)
    }
}