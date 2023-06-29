package com.example.mysterymind.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.mysterymind.model.entity.ZodiacSignCompatibility

@Dao
interface ZodiacDao {
    @Query("SELECT * FROM Zodiac_Sign_Compatibility WHERE zodiac_sign = :sign")
    fun findZodiacSignCompatibility(sign: String): List<ZodiacSignCompatibility>
    // Інші методи DAO (оновлення, видалення і т.д.)
}