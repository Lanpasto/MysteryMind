package com.example.mysterymind.controller.classofscreen

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.example.mysterymind.R
import com.example.mysterymind.services.analyticActivity.AnalyticProcessor
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class AnalyticActivity : AppCompatActivity() {
    private lateinit var showHoroscopeButton: Button
    lateinit var maleNameEditText: EditText
    private lateinit var textView: TextView
    lateinit var textView4: TextView
    lateinit var maleZodiacSpinner: Spinner
    lateinit var femaleZodiacSpinner: Spinner
    lateinit var femaleNameEditText: EditText
    private lateinit var nextButton:Button
    private lateinit var progressBar: CircularProgressBar
    private lateinit var analyticProcessor: AnalyticProcessor

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analytic)

        showHoroscopeButton = findViewById(R.id.button)
        maleNameEditText = findViewById(R.id.male_name_edittext)

        textView4 = findViewById(R.id.textView4)
        nextButton = findViewById(R.id.nextButton)
        maleZodiacSpinner = findViewById(R.id.male_zodiac_spinner)
        femaleZodiacSpinner = findViewById(R.id.female_zodiac_spinner)
        femaleNameEditText = findViewById(R.id.female_name_edittext)
        progressBar = findViewById(R.id.compatibilityProgressBar)

        // Ініціалізація об'єкта для обробки бізнес-логіки
        analyticProcessor = AnalyticProcessor(this)
        nextButton.isEnabled = false
        nextButton.setOnClickListener {
            analyticProcessor.showInputDialog()
        }
        showHoroscopeButton.setOnClickListener {
            showHoroscopeButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_press_animation))
            // Виклик методу для оновлення TextView
            analyticProcessor.updateTextView()
            nextButton.isEnabled = true
        }
    }


    fun setTextView4Text(text: String) {
        textView4.text = text
    }

    fun setProgressBarProgress(progress: Float) {
        progressBar.setProgressWithAnimation(progress, 1000)
    }

}


