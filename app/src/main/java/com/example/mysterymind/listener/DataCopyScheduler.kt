package com.example.mysterymind.listener

import com.example.mysterymind.model.entity.RandomEvent
import com.example.mysterymind.model.dao.RandomEventDao
import com.example.mysterymind.model.entity.TodayPredict
import com.example.mysterymind.model.dao.TodayPredictDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

class DataCopyScheduler(
    private val randomEventDao: RandomEventDao,
    private val todayPredictDao: TodayPredictDao
) {
    private var timer: Timer? = null

    // Метод для початку автоматичного копіювання
    fun start() {
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                copyData()
            }
        }, 0, 10000) // Копіювати кожні 10 секунд
    }

    // Метод для зупинки автоматичного копіювання
    fun stop() {
        timer?.cancel()
        timer = null
    }

    // Метод для копіювання даних
    private fun copyData() {
        CoroutineScope(Dispatchers.IO).launch {
            val randomEvents: List<RandomEvent> = randomEventDao.getAllRandomEvents()
            if (randomEvents.isNotEmpty()) {
                val todayPredicts = randomEvents.take(5).map { randomEvent ->
                    TodayPredict(
                        zodiac = randomEvent.day,
                        horoscopeText = randomEvent.horoscopeText
                    )
                }
                todayPredictDao.deleteAllTodayPredicts()
                todayPredictDao.insertTodayPredicts(todayPredicts)
            }
        }
    }
}



