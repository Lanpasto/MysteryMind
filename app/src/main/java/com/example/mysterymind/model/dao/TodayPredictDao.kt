package com.example.mysterymind.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mysterymind.model.entity.RandomEvent
import com.example.mysterymind.model.entity.TodayPredict

@Dao
interface TodayPredictDao {
    @Insert
    fun insertTodayPredict(todayPredict: TodayPredict)

    @Query("SELECT * FROM Today_Predict LIMIT 1")
    fun getFirstTodayPredict(): TodayPredict?


    // Оновлення першого рядка в таблиці Today_Predict
    @Query("UPDATE Today_Predict SET horoscope_text = :horoscopeText WHERE id = (SELECT MIN(id) FROM Today_Predict)")
    suspend fun updateFirstTodayPredictHoroscopeText(horoscopeText: String)
    @Query("UPDATE Random_Event SET horoscope_text = :horoscopeText WHERE id = (SELECT MIN(id) FROM Random_Event)")
    suspend fun updateFirstRandomEventHoroscopeText(horoscopeText: String)



    @Query("SELECT * FROM Random_Event ORDER BY RANDOM() LIMIT 1")
    fun getRandomEvent(): RandomEvent

    @Query("DELETE FROM Today_Predict")
    fun deleteAllTodayPredicts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodayPredicts(todayPredicts: List<TodayPredict>)
}