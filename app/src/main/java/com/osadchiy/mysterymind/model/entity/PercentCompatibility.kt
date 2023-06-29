package com.example.mysterymind.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Percent_Compatibility")
data class PercentCompatibility(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "percent_compatibility")
    val percentCompatibility: Int,
    @ColumnInfo(name = "character_of_percent_compatibility")
    val characterOfPercentCompatibility: String
)