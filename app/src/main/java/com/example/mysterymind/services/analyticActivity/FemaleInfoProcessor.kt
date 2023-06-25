package com.example.mysterymind.services.analyticActivity

import android.util.Log
import com.example.mysterymind.model.dao.NameDao
import com.example.mysterymind.model.entity.NameSignCompatibility
import com.example.mysterymind.model.dao.ZodiacDao
import com.example.mysterymind.model.entity.ZodiacSignCompatibility


class FemaleInfoProcessor(private val nameDao: NameDao, private val zodiacDao: ZodiacDao) {
    fun getCompatibility(name: String): NameSignCompatibility? {
        val compatibility = nameDao.findByName(name)
        Log.d("FemaleInfoProcessor", "Compatibility for name '$name': $compatibility")
        return compatibility
    }

    fun getZodiacSignCompatibility(zodiacSign: String): List<ZodiacSignCompatibility> {
        val compatibilityList = zodiacDao.findZodiacSignCompatibility(zodiacSign)
        Log.d("FemaleInfoProcessor", "Zodiac sign compatibility for '$zodiacSign': $compatibilityList")
        return compatibilityList
    }
}