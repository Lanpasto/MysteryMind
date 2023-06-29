package com.example.mysterymind.services.mainActivity

import android.content.Intent
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mysterymind.R
import com.example.mysterymind.controllerTest.classofscreen.AnalyticActivity
import com.example.mysterymind.controllerTest.classofscreen.HoroscopeActivity
import com.example.mysterymind.controllerTest.classofscreen.KarmaQuestActivity

class MainActivityProcessor(private val activity: AppCompatActivity) {

    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var button4: Button

    var animation: Animation = AnimationUtils.loadAnimation(activity, R.anim.translate)

    fun initializeViews() {
        button2 = activity.findViewById(R.id.button_quest)
        button3 = activity.findViewById(R.id.button_anal)
        button4 = activity.findViewById(R.id.button_horoscope)
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