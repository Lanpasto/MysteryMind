package com.example.mysterymind.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Random_Event")
data class RandomEvent(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val day: String,
    @ColumnInfo(name = "horoscope_text")
    val horoscopeText: String
)

@Entity(tableName = "Aquarius")
data class Aquarius(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "day")
    val day: String,
    @ColumnInfo(name = "horoscope_text")
    val horoscopeText: String,
)

@Entity(tableName = "Libra")
data class Libra(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val day: String,
    @ColumnInfo(name = "horoscope_text")
    val horoscopeText: String
)

@Entity(tableName = "Scorpio")
data class Scorpio(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val day: String,
    @ColumnInfo(name = "horoscope_text")
    val horoscopeText: String
)

@Entity(tableName = "Taurus")
data class Taurus(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val day: String,
    @ColumnInfo(name = "horoscope_text")
    val horoscopeText: String
)

@Entity(tableName = "Gemini")
data class Gemini(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val day: String,
    @ColumnInfo(name = "horoscope_text")
    val horoscopeText: String
)

@Entity(tableName = "Leo")
data class Leo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val day: String,
    @ColumnInfo(name = "horoscope_text")
    val horoscopeText: String
)

@Entity(tableName = "Virgo")
data class Virgo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val day: String,
    @ColumnInfo(name = "horoscope_text")
    val horoscopeText: String
)

@Entity(tableName = "Cancer")
data class Cancer(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val day: String,
    @ColumnInfo(name = "horoscope_text")
    val horoscopeText: String
)

@Entity(tableName = "Aries")
data class Aries(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val day: String,
    @ColumnInfo(name = "horoscope_text")
    val horoscopeText: String
)

@Entity(tableName = "Capricorn")
data class Capricorn(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val day: String,
    @ColumnInfo(name = "horoscope_text")
    val horoscopeText: String
)

@Entity(tableName = "Pisces")
data class Pisces(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val day: String,
    @ColumnInfo(name = "horoscope_text")
    val horoscopeText: String
)

@Entity(tableName = "Sagittarius")
data class Sagittarius(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val day: String,
    @ColumnInfo(name = "horoscope_text")
    val horoscopeText: String
)
