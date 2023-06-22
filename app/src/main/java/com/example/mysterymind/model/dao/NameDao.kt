package com.example.mysterymind.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.mysterymind.model.entity.NameSignCompatibility

@Dao
interface NameDao {
    @Query("SELECT * FROM NAME_Sign_Compatibility WHERE name_sign LIKE '%' || :name || '%' ORDER BY id ASC LIMIT 1")
    fun findByName(name: String): NameSignCompatibility?


}