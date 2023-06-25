package com.example.mysterymind.services.horoscopeactivity

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.example.mysterymind.R
import com.example.mysterymind.controller.classofscreen.HoroscopeActivity
import com.example.mysterymind.controller.splashLoadScreen.CustomSpinner
import com.example.mysterymind.data.AppDatabase
import com.example.mysterymind.model.dao.HoroscopeDao
import com.example.mysterymind.model.entity.RandomEvent
import java.text.SimpleDateFormat
import java.util.Locale

class HoroscopeProcessor(private val context: Context) {
    private val horoscopeDao: HoroscopeDao

    init {
        val database = AppDatabase.getInstance(context)
        horoscopeDao = database.horoscopeDao()
    }

    fun onButtonClicked(selectedDateStr: String, callback: (RandomEvent?) -> Unit) {
        val selectedSign = getSelectedSign()
        val selectedDay = getSelectedDay(selectedDateStr)

        val selectedDao = getSelectedDao(selectedSign, selectedDay)
        selectedDao?.observe(context as LifecycleOwner) { events ->
            val event = events?.get(0)
            callback(event)
        }
    }

    private fun getSelectedSign(): String {
        val spinner = (context as HoroscopeActivity).findViewById<CustomSpinner>(R.id.spinner)
        val selectedItem = spinner.selectedItem as CustomSpinner.SpinnerItem
        return selectedItem.text
    }

    private fun getSelectedDay(selectedDateStr: String): String {
        val dateFormatter = SimpleDateFormat("MM/dd", Locale.getDefault())
        val selectedDate = dateFormatter.parse(selectedDateStr)
        return dateFormatter.format(selectedDate!!)
    }

    private fun getSelectedDao(selectedSign: String, selectedDay: String): LiveData<List<RandomEvent>>? {
        return when (selectedSign) {
            "Capricorn" -> {
                Log.d("HoroscopeProcessor", "Selected sign: Capricorn")
                horoscopeDao.getRandomEventsForCapricorn(selectedDay)
            }
            "Gemini" -> {
                Log.d("HoroscopeProcessor", "Selected sign: Gemini")
                horoscopeDao.getRandomEventsForGemini(selectedDay)
            }
            "Leo" -> {
                Log.d("HoroscopeProcessor", "Selected sign: Leo")
                horoscopeDao.getRandomEventsForLeo(selectedDay)
            }
            "Libra" -> {
                Log.d("HoroscopeProcessor", "Selected sign: Libra")
                horoscopeDao.getRandomEventsForLibra(selectedDay)
            }
            "Pisces" -> {
                Log.d("HoroscopeProcessor", "Selected sign: Pisces")
                horoscopeDao.getRandomEventsForPisces(selectedDay)
            }
            "Sagittarius" -> {
                Log.d("HoroscopeProcessor", "Selected sign: Sagittarius")
                horoscopeDao.getRandomEventsForSagittarius(selectedDay)
            }
            "Scorpio" -> {
                Log.d("HoroscopeProcessor", "Selected sign: Scorpio")
                horoscopeDao.getRandomEventsForScorpio(selectedDay)
            }
            "Taurus" -> {
                Log.d("HoroscopeProcessor", "Selected sign: Taurus")
                horoscopeDao.getRandomEventsForTaurus(selectedDay)
            }
            "Virgo" -> {
                Log.d("HoroscopeProcessor", "Selected sign: Virgo")
                horoscopeDao.getRandomEventsForVirgo(selectedDay)
            }
            "Aries" -> {
                Log.d("HoroscopeProcessor", "Selected sign: Aries")
                horoscopeDao.getRandomEventsForAries(selectedDay)
            }
            "Cancer" -> {
                Log.d("HoroscopeProcessor", "Selected sign: Cancer")
                horoscopeDao.getRandomEventsForCancer(selectedDay)
            }
            "Aquarius" -> {
                Log.d("HoroscopeProcessor", "Selected sign: Aquarius")
                horoscopeDao.getRandomEventsForAquarius(selectedDay)
            }
            else -> {
                Log.d("HoroscopeProcessor", "Selected sign: Unknown")
                null
            }
        }
    }
}

/*val currentTime = Calendar.getInstance().time
       val dateFormatter = SimpleDateFormat("dd/MM", Locale.getDefault())*/
/*     selectedDay = dateFormatter.format(currentTime)*/