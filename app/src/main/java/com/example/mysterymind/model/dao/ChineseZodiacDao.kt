package com.example.mysterymind.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.mysterymind.model.entity.ChineseSignCompatibility

@Dao
interface ChineseZodiacDao {
    @Query("SELECT * FROM Chinese_Sign_Compatibility WHERE chinese_sign = :sign")
    fun findZodiacSignCompatibility(sign: String): List<ChineseSignCompatibility>
}
