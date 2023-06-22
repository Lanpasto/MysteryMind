package com.example.mysterymind.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mysterymind.model.entity.RandomEvent


@Dao
interface RandomEventDao {
    // ...

    @Insert
    suspend fun insertRandomEvent(randomEvent: RandomEvent): Long

    @Update
    suspend fun updateRandomEvent(randomEvent: RandomEvent)

    @Delete
    suspend fun deleteRandomEvent(randomEvent: RandomEvent)

    @Query("SELECT * FROM Random_Event ORDER BY id LIMIT 1 OFFSET :offset")
    fun getRandomEventByOffset(offset: Int): RandomEvent?

    @Query("SELECT * FROM Random_Event ORDER BY RANDOM() LIMIT 1")
    fun getAllRandomEvents1(): LiveData<List<RandomEvent>>

    @Query("SELECT * FROM Random_Event ORDER BY RANDOM() LIMIT 1")
    fun getAllRandomEvents(): List<RandomEvent>

    // ...
}


