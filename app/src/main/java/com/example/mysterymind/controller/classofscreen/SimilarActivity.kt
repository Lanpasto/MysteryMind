package com.example.mysterymind.controller.classofscreen

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mysterymind.R
import com.example.mysterymind.data.AppDatabase
import com.example.mysterymind.model.dao.ChineseZodiacDao
import com.example.mysterymind.model.dao.ZodiacDao
import com.example.mysterymind.services.similaractivity.ChineseZodiacCalculator
import com.example.mysterymind.services.similaractivity.FormattedEditText
import com.example.mysterymind.services.similaractivity.GreekZodiacCalculator
import com.example.mysterymind.services.similaractivity.ProcessSimilar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SimilarActivity : AppCompatActivity() {
    private lateinit var dobEditText: FormattedEditText
    private lateinit var dobEditText1: FormattedEditText
    private lateinit var textView: TextView
    private val chineseZodiacCalculator = ChineseZodiacCalculator()
    private val greekZodiacCalculator = GreekZodiacCalculator()
    private lateinit var processSimilar: ProcessSimilar
    private lateinit var zodiacDao: ZodiacDao
    private lateinit var chineseZodiacDao: ChineseZodiacDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job)

        dobEditText = FormattedEditText(findViewById(R.id.birth_date_edit_text))
        dobEditText1 = FormattedEditText(findViewById(R.id.editTextDOB))
        textView = findViewById(R.id.textView3)

        dobEditText.setupFormatting()
        dobEditText1.setupFormatting()

        val database = AppDatabase.getInstance(this)
        zodiacDao = database.zodiacDao()
        chineseZodiacDao = database.chineseZodiacDao()
        processSimilar = ProcessSimilar(zodiacDao, chineseZodiacDao)

        val buttonCheck: Button = findViewById(R.id.buttonCheck)
        buttonCheck.setOnClickListener {
            val date = dobEditText.editText?.text.toString()
            val zodiacSign = greekZodiacCalculator.calculateZodiacSign(date)

            val date1 = dobEditText1.editText?.text.toString()
            val zodiacSign1 = greekZodiacCalculator.calculateZodiacSign(date1)
            println(zodiacSign1 + "1")
            println(zodiacSign + "1")
            val zodiacSign22 = chineseZodiacCalculator.calculateZodiacSign(date)
            val zodiacSign21 = chineseZodiacCalculator.calculateZodiacSign(date1)
            textView.text = "Your Chinese Zodiac Sign: $zodiacSign"
            println(zodiacSign21 + "1")
            println(zodiacSign22 + "1")
            CoroutineScope(Dispatchers.Main).launch {
                val compatibility1 = withContext(Dispatchers.IO) {
                    processSimilar.determineZodiacCompatibility1(zodiacSign, zodiacSign1)
                }
                println(compatibility1)

                val compatibility2 = withContext(Dispatchers.IO) {
                    processSimilar.determineZodiacCompatibility1(zodiacSign21, zodiacSign22)
                }
                println(compatibility2)
                val totalCompatibility = compatibility1 + compatibility2
                makeNumberInteresting(totalCompatibility)
                println(makeNumberInteresting(totalCompatibility))
            }


        }
    }
    fun makeNumberInteresting(number: Int): Int {
        if (number <= 100) {
            return number*6
        }

        val digits = number.toString().toCharArray().map { it.toString().toInt() }
        val sum = digits.sum()

        return if (sum <= 100) {
            sum
        } else {
            makeNumberInteresting(sum)
        }
    }
}

