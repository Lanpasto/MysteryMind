package com.example.mysterymind.services.mainActivity

import android.content.Intent
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mysterymind.R
import com.example.mysterymind.controller.classofscreen.AnalyticActivity
import com.example.mysterymind.controller.classofscreen.HoroscopeActivity
import com.example.mysterymind.controller.classofscreen.KarmaQuestActivity

class MyActivity(private val activity: AppCompatActivity) {

    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button

    private val animation: Animation = AnimationUtils.loadAnimation(activity, R.anim.translate)

    fun initializeViews() {
        button2 = activity.findViewById(R.id.button2)
        button3 = activity.findViewById(R.id.button3)
        button4 = activity.findViewById(R.id.button4)
    }

    fun setButtonClickListeners() {
        button2.setOnClickListener {
            button2.startAnimation(animation)
            Log.d("MyActivity", "Button 2 clicked")
            val intent = Intent(activity, KarmaQuestActivity::class.java)
            activity.startActivity(intent)
        }

        button3.setOnClickListener {
            button3.startAnimation(animation)
            Log.d("MyActivity", "Button 3 clicked")
            val intent = Intent(activity, AnalyticActivity::class.java)
            activity.startActivity(intent)
        }

        button4.setOnClickListener {
            button4.startAnimation(animation)
            Log.d("MyActivity", "Button 4 clicked")
            val intent = Intent(activity, HoroscopeActivity::class.java)
            activity.startActivity(intent)
        }
    }
}