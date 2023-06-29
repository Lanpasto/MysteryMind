package com.example.mysterymind.controllerTest.classofscreen


import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.mysterymind.R
import com.example.mysterymind.controllerTest.splashLoadScreen.CustomSpinner
import com.example.mysterymind.services.horoscopeActivity.HoroscopeProcessor
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HoroscopeActivity : AppCompatActivity() {
    private lateinit var horoscopeProcessor: HoroscopeProcessor
    private lateinit var textView: TextView
    private lateinit var textView3: TextView

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId", "ClickableViewAccessibility", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horoscope)
        val button = findViewById<Button>(R.id.button)
        textView = findViewById(R.id.textView2)
        textView3 = findViewById(R.id.textView3)

        val spinner = findViewById<CustomSpinner>(R.id.spinner)

        horoscopeProcessor = HoroscopeProcessor(this)

        button.setOnClickListener {
            button.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_press_animation))
            val currentDate = LocalDate.now()
            val textNotFound =
                "Сьогодні ваш день буде сповнений позитивної енергії та можливостей. Ви будете мати велику силу впливу на оточуючих і зможете досягти значних результатів у своїх справах. Будьте відкриті до нових ідей і можливостей, які з'являться перед вами. Ваша творчість буде на висоті, тому скористайтеся цим для розвитку своїх проектів та досягнення мети. Будьте уважні до своєї інтуїції, вона буде надійним провідником у вашому житті. Насолоджуйтесь цим чудовим днем і даруйте позитивні емоції оточуючим."

            val selectedDateStr =
                currentDate.format(DateTimeFormatter.ofPattern("MM/dd"))
            horoscopeProcessor.onButtonClicked(selectedDateStr) { event ->
                if (event != null) {
                    textView.text = event.horoscopeText
                    textView3.text = "Сьогодні"
                    activateSwipeChangeText()
                } else {
                    textView.text = textNotFound
                }
            }
            Log.d("HoroscopeActivity", "Button clicked")
        }


        val zodiacSigns = resources.getStringArray(R.array.zodiac_signs)
        val zodiacImages = resources.obtainTypedArray(R.array.zodiac_images)
        val spinnerItems = mutableListOf<CustomSpinner.SpinnerItem>()

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                Log.d("HoroscopeActivity", "Spinner item selected: $position")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d("HoroscopeActivity", "Spinner item unselected")
            }
        }

        for (i in zodiacSigns.indices) {
            val sign = zodiacSigns[i]
            val imageResId = zodiacImages.getResourceId(i, 0)
            val spinnerItem = CustomSpinner.SpinnerItem(sign, imageResId)
            spinnerItems.add(spinnerItem)
        }

        zodiacImages.recycle()

        spinner.setItems(spinnerItems)
        spinner.setSelection(0)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    private fun activateSwipeChangeText() {
        val texts = arrayOf("Сьогодні", "Завтра")
        var currentIndex = 0
        var startX = 0f
        var isDragging = false

        val rootView = findViewById<View>(android.R.id.content)
        val textView3 = findViewById<TextView>(R.id.textView3)
        val textView = findViewById<TextView>(R.id.textView2)

        rootView.setOnTouchListener { _, touchEvent ->
            when (touchEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Початок дотику пальцем
                    startX = touchEvent.x
                    isDragging = false
                }

                MotionEvent.ACTION_MOVE -> {
                    // Рух пальцем
                    val diffX = touchEvent.x - startX
                    if (diffX < -50) {
                        // Проведення пальцем вліво
                        if (!isDragging && currentIndex < texts.size - 1) {
                            currentIndex++
                            textView3.text = texts[currentIndex]
                            isDragging = true
                        }
                    } else if (diffX > 50) {
                        // Проведення пальцем вправо
                        if (!isDragging && currentIndex > 0) {
                            currentIndex--
                            textView3.text = texts[currentIndex]
                            isDragging = true
                        }
                    }

                    val selectedDateStr = if (texts[currentIndex] == "Сьогодні") {
                        LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd"))
                    } else {
                        LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("MM/dd"))
                    }

                    horoscopeProcessor.onButtonClicked(selectedDateStr) { horoscopeEvent ->
                        if (horoscopeEvent != null) {
                            textView.text = horoscopeEvent.horoscopeText
                        } else {
                            textView.text = "No random event found"
                        }
                    }
                    Log.d(
                        "HoroscopeActivity", "Text swiped. Current index: $currentIndex"
                    ) // Логування події
                }

                MotionEvent.ACTION_UP -> {
                    startX = 0f
                    isDragging = false
                }
            }
            true
        }
    }

}





