package com.geekbrains.myapplication

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toastMe(view: View) {
        val myToast = Toast.makeText(this, "Just a toast", Toast.LENGTH_LONG)
        myToast.show()
    }

    fun countMe(view: View) {
        toastMe(view)
        val tv = findViewById<TextView>(R.id.tv)
        val countString = tv.text.toString()
        var count: Int = Integer.parseInt(countString)
        count++
        tv.text = count.toString();
    }
}