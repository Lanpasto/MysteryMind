package com.example.mysterymind.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NAME_Sign_Compatibility")
data class NameSignCompatibility(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name_sign")
    val nameSign: String,
    val compatibility: Int
)