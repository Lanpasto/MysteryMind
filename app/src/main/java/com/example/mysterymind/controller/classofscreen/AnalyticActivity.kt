package com.example.mysterymind.controller.classofscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mysterymind.R
import com.example.mysterymind.controller.splashLoadScreen.CustomSpinner
import com.example.mysterymind.services.analyticActivity.AnalyticProcessor
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class AnalyticActivity : AppCompatActivity() {
    private lateinit var showHoroscopeButton: Button
    lateinit var maleNameEditText: EditText
    lateinit var textView4: TextView
    lateinit var maleZodiacSpinner: CustomSpinner
    lateinit var femaleZodiacSpinner: CustomSpinner
    lateinit var femaleNameEditText: EditText
    private lateinit var nextButton: Button
    private lateinit var progressBar: CircularProgressBar
    private lateinit var analyticProcessor: AnalyticProcessor

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_analytic)
        femaleNameEditText = findViewById(R.id.female_name_edittext)
        showHoroscopeButton = findViewById(R.id.button)
        maleNameEditText = findViewById(R.id.male_name_edittext)
        textView4 = findViewById(R.id.textView4)
        nextButton = findViewById(R.id.nextButton)
        maleZodiacSpinner = findViewById(R.id.male_zodiac_spinner)
        femaleZodiacSpinner = findViewById(R.id.female_zodiac_spinner)
        progressBar = findViewById(R.id.compatibilityProgressBar)

        // Ініціалізація об'єкта для обробки бізнес-логіки
        analyticProcessor = AnalyticProcessor(this)
        nextButton.isEnabled = false
        nextButton.setOnClickListener {
            analyticProcessor.showInputDialog()
            Log.d("AnalyticActivity", "Next button clicked")

        }
        showHoroscopeButton.setOnClickListener {
            showHoroscopeButton.startAnimation(
                AnimationUtils.loadAnimation(
                    this,
                    R.anim.button_press_animation
                )
            )
            // Виклик методу для оновлення TextView
            analyticProcessor.updateTextView()
            nextButton.isEnabled = true

            Log.d("AnalyticActivity", "onCreate() called")

        }

        val zodiacSigns = resources.getStringArray(R.array.zodiac_signs)
        val zodiacImages = resources.obtainTypedArray(R.array.zodiac_images)
        val spinnerItems = mutableListOf<CustomSpinner.SpinnerItem>()

        val onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {}

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        maleZodiacSpinner.onItemSelectedListener = onItemSelectedListener
        femaleZodiacSpinner.onItemSelectedListener = onItemSelectedListener

        for (i in zodiacSigns.indices) {
            val sign = zodiacSigns[i]
            val imageResId = zodiacImages.getResourceId(i, 0)
            val spinnerItem = CustomSpinner.SpinnerItem(sign, imageResId)
            spinnerItems.add(spinnerItem)
        }

        zodiacImages.recycle()

        maleZodiacSpinner.setItems(spinnerItems)
        maleZodiacSpinner.setSelection(0)
        femaleZodiacSpinner.setItems(spinnerItems)
        femaleZodiacSpinner.setSelection(0)

    }

    fun setTextView4Text(text: String) {
        textView4.text = text
        Log.d("AnalyticActivity", "setTextView4Text() called with text: $text")

    }

    fun setProgressBarProgress(progress: Float) {
        progressBar.setProgressWithAnimation(progress, 1000)
        Log.d("AnalyticActivity", "setProgressBarProgress() called with progress: $progress")

    }
}




