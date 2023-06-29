package com.example.mysterymind.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.mysterymind.model.entity.KarmaAssignment

@Dao
interface KarmaDao {
    @Query("SELECT * FROM KarmaAssignment ORDER BY RANDOM() LIMIT 1")
    fun getRandomAssignment(): KarmaAssignment
}
