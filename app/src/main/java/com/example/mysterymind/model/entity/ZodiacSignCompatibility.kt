package com.example.mysterymind.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Zodiac_Sign_Compatibility")
data class ZodiacSignCompatibility(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "zodiac_sign")
    val zodiacSign: String,
    val compatibility: Int,
    val similar: Int
)