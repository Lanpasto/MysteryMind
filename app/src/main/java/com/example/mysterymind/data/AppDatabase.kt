package com.example.mysterymind.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mysterymind.model.dao.HoroscopeDao
import com.example.mysterymind.model.dao.KarmaDao
import com.example.mysterymind.model.dao.NameDao
import com.example.mysterymind.model.dao.ZodiacDao
import com.example.mysterymind.model.entity.Aquarius
import com.example.mysterymind.model.entity.Aries
import com.example.mysterymind.model.entity.Cancer
import com.example.mysterymind.model.entity.Capricorn
import com.example.mysterymind.model.entity.Gemini
import com.example.mysterymind.model.entity.KarmaAssignment
import com.example.mysterymind.model.entity.Leo
import com.example.mysterymind.model.entity.Libra
import com.example.mysterymind.model.entity.NameSignCompatibility
import com.example.mysterymind.model.entity.PercentCompatibility
import com.example.mysterymind.model.entity.Pisces
import com.example.mysterymind.model.entity.RandomEvent
import com.example.mysterymind.model.entity.Sagittarius
import com.example.mysterymind.model.entity.Scorpio
import com.example.mysterymind.model.entity.Taurus
import com.example.mysterymind.model.entity.Virgo
import com.example.mysterymind.model.entity.ZodiacSignCompatibility

@Database(
    entities = [
        RandomEvent::class,
        Aquarius::class,
        Libra::class,
        Scorpio::class,
        Taurus::class,
        Gemini::class,
        Leo::class,
        Virgo::class,
        Cancer::class,
        Aries::class,
        Capricorn::class,
        Pisces::class,
        Sagittarius::class,
        ZodiacSignCompatibility::class,
        NameSignCompatibility::class,
        PercentCompatibility::class,
        KarmaAssignment::class
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun nameDao(): NameDao
    abstract fun zodiacDao(): ZodiacDao
    abstract fun horoscopeDao(): HoroscopeDao
    abstract fun karmaDao(): KarmaDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "mysterymind.db"
                    )
                        .createFromAsset("mysterymind.db")
                        .fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE!!
        }
    }
}






