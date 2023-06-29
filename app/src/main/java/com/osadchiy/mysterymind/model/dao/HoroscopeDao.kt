package com.example.mysterymind.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.mysterymind.model.entity.RandomEvent

@Dao
interface HoroscopeDao : RandomEventDao {

    @Query("SELECT * FROM Aries WHERE day = :selectedDay")
    fun getRandomEventsForAries(selectedDay: String): LiveData<List<RandomEvent>>

    @Query("SELECT * FROM Cancer WHERE day = :selectedDay")
    fun getRandomEventsForCancer(selectedDay: String): LiveData<List<RandomEvent>>

    @Query("SELECT * FROM Virgo WHERE day = :selectedDay")
    fun getRandomEventsForVirgo(selectedDay: String): LiveData<List<RandomEvent>>

    @Query("SELECT * FROM Capricorn WHERE day = :selectedDay")
    fun getRandomEventsForCapricorn(selectedDay: String): LiveData<List<RandomEvent>>

    @Query("SELECT * FROM Scorpio WHERE day = :selectedDay")
    fun getRandomEventsForScorpio(selectedDay: String): LiveData<List<RandomEvent>>

    @Query("SELECT * FROM Taurus WHERE day = :selectedDay")
    fun getRandomEventsForTaurus(selectedDay: String): LiveData<List<RandomEvent>>

    @Query("SELECT * FROM Leo WHERE day = :selectedDay")
    fun getRandomEventsForLeo(selectedDay: String): LiveData<List<RandomEvent>>

    @Query("SELECT * FROM Aquarius WHERE day = :selectedDay")
    fun getRandomEventsForAquarius(selectedDay: String): LiveData<List<RandomEvent>>

    @Query("SELECT * FROM Libra WHERE day = :selectedDay")
    fun getRandomEventsForLibra(selectedDay: String): LiveData<List<RandomEvent>>

    @Query("SELECT * FROM Sagittarius WHERE day = :selectedDay")
    fun getRandomEventsForSagittarius(selectedDay: String): LiveData<List<RandomEvent>>

    @Query("SELECT * FROM Gemini WHERE day = :selectedDay")
    fun getRandomEventsForGemini(selectedDay: String): LiveData<List<RandomEvent>>

    @Query("SELECT * FROM Pisces WHERE day = :selectedDay")
    fun getRandomEventsForPisces(selectedDay: String): LiveData<List<RandomEvent>>
}

