package com.example.mysterymind.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "KarmaAssignment")
data class KarmaAssignment(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "karma")
    val karma: Int,
    @ColumnInfo(name = "text_of_Assignment")
    val textOfAssignment: String
)