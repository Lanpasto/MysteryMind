package com.example.mysterymind.controller.classofscreen


import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mysterymind.R
import com.example.mysterymind.services.horoscopeactivity.HoroscopeProcessor


class HoroscopeActivity : AppCompatActivity() {
    private lateinit var horoscopeProcessor: HoroscopeProcessor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horoscope)

        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.textView2)

        horoscopeProcessor = HoroscopeProcessor(this)

        button.setOnClickListener {
            button.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_press_animation))

            horoscopeProcessor.onButtonClicked { event ->
                if (event != null) {
                    textView.text = event.horoscopeText
                } else {
                    textView.text = "No random event found"
                }
            }
        }
    }
}



/*val currentTime = Calendar.getInstance().time
       val dateFormatter = SimpleDateFormat("dd/MM", Locale.getDefault())*/
/*     selectedDay = dateFormatter.format(currentTime)*/