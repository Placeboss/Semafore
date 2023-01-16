package com.example.semafor

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class MainActivity : Activity() {
    var imSemafor:ImageView? = null
    var counter:Int = 0
    private var timer: Timer? = null
    private var is_run = false
    private lateinit var btStartStop: Button
    private lateinit var tvColor: TextView
    var imageArray:IntArray = intArrayOf(R.drawable.red,R.drawable.yellow, R.drawable.green)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imSemafor = findViewById(R.id.imSemafor)
        btStartStop = findViewById(R.id.btStartStop)
        tvColor = findViewById(R.id.tvColor)

    }
    fun onClickStartStop(view: View){
        view as Button
        if(!is_run){
            startStop()

            btStartStop.setText(R.string.stop)
            btStartStop.setBackgroundResource(R.drawable.button_style_stop)
            is_run = true
        }
        else
        {
            btStartStop.setText(R.string.start)
            btStartStop.setBackgroundResource(R.drawable.button_style)
            timer?.cancel()
            is_run = false
            counter = 0
        }
    }
    private fun startStop(){
        timer = Timer()
        timer?.schedule(object : TimerTask(){
            override fun run() {
                runOnUiThread {
                    imSemafor?.setImageResource(imageArray[counter])
                    counter++
                    if(counter == 3)counter = 0
                    when (counter){
                        1 -> {  tvColor.setText(R.string.red)
                                tvColor.setTextColor(Color.parseColor("#D62828"))}
                        2 -> {  tvColor.setText(R.string.yellow)
                                tvColor.setTextColor(Color.parseColor("#FFA200"))}
                        0 -> {  tvColor.setText(R.string.green)
                                tvColor.setTextColor(Color.parseColor("#008000"))}
                        else -> tvColor.setText(R.string.click)
                    }
                }
            }

        }, 0, 500)
    }
}