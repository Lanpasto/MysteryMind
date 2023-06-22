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

@Entity(tableName = "Today_Predict")
data class TodayPredict(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val zodiac: String,
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

@Entity(tableName = "Zodiac_Sign_Compatibility")
data class ZodiacSignCompatibility(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "zodiac_sign")
    val zodiacSign: String,
    val compatibility: Int,
    val similar: Int
)
@Entity(tableName = "Chinese_Sign_Compatibility")
data class ChineseSignCompatibility(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "chinese_sign")
    val chineseSign: String,
    val compatibility: Int,
    val similar: Int
)
@Entity(tableName = "NAME_Sign_Compatibility")
data class NameSignCompatibility(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name_sign")
    val nameSign: String,
    val compatibility: Int
)

@Entity(tableName = "Percent_Compatibility")
data class PercentCompatibility(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "percent_compatibility")
    val percentCompatibility: Int,
    @ColumnInfo(name = "character_of_percent_compatibility")
    val characterOfPercentCompatibility: String
)

@Entity(tableName = "KarmaAssignment")
data class KarmaAssignment(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "karma")
    val karma: Int,
    @ColumnInfo(name = "text_of_Assignment")
    val textOfAssignment: String
)









