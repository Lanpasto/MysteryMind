package com.example.mysterymind.listener

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import com.example.mysterymind.data.AppDatabase
import com.example.mysterymind.model.entity.RandomEvent
import com.example.mysterymind.model.dao.RandomEventDao
import com.example.mysterymind.model.entity.TodayPredict
import com.example.mysterymind.model.dao.TodayPredictDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyService : Service() {
    private lateinit var randomEventDao: RandomEventDao
    private lateinit var todayPredictDao: TodayPredictDao
    private val handler = Handler(Looper.getMainLooper())
    private val dataCopyRunnable = object : Runnable {
        override fun run() {
            copyData()
            scheduleDataCopy() // Копіювати знову через 24 години
        }
    }

    override fun onCreate() {
        super.onCreate()
        // Ініціалізуйте необхідні екземпляри DAO або інші залежності тут
        val database = AppDatabase.getInstance(this)
        randomEventDao = database.randomEventDao()
        todayPredictDao = database.todayPredictDao()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("MyService", "Service started")
        // Планування запуску копіювання даних
        scheduleDataCopy()
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        // Метод onBind() має бути перевизначений, якщо ви створюєте прив'язану службу
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        // Зупинити розклад копіювання даних при знищенні служби
        handler.removeCallbacks(dataCopyRunnable)
    }

    // Метод для планування копіювання даних через 24 години
    private fun scheduleDataCopy() {
        handler.removeCallbacks(dataCopyRunnable)
        val delayMillis =  24 * 60 * 60 * 1000L // 24 години
        handler.postDelayed(dataCopyRunnable, delayMillis)
    }

    // Метод для копіювання даних
    private fun copyData() {
        CoroutineScope(Dispatchers.IO).launch {
            val randomEvent: RandomEvent? = randomEventDao.getRandomEventByOffset(0)
            if (randomEvent != null) {
                val todayPredict = TodayPredict(
                    zodiac = randomEvent.day,
                    horoscopeText = randomEvent.horoscopeText
                )
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val horoscopeText = "Нове значення horoscopeText" // Замініть на власне значення
                        todayPredictDao.updateFirstTodayPredictHoroscopeText(horoscopeText)
                        val horoscopeText1 = "Новий текст гороскопу"
                        todayPredictDao.updateFirstRandomEventHoroscopeText(horoscopeText1)
                        Log.d("MyService", "Перший рядок в таблиці Today_Predict оновлено успішно")

                        val firstTodayPredict = todayPredictDao.getFirstTodayPredict()
                        if (firstTodayPredict != null) {
                            Log.d("MyService", "Перший рядок в таблиці Today_Predict: ${firstTodayPredict.toString()}")
                        } else {
                            Log.d("MyService", "Таблиця Today_Predict порожня")
                        }
                    } catch (e: Exception) {
                        Log.e("MyService", "Помилка при оновленні першого рядка в таблиці Today_Predict: ${e.message}")
                    }
                }



                Log.d("MyService", "Zodiac: ${todayPredict}, Horoscope Text: ${todayPredict.horoscopeText}")
                Log.d("MyService", "Data copied successfully")
                Log.d("MyService", "Insert successful")
            }
        }
    }
}





