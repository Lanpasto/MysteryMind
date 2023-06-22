package com.example.mysterymind.services.similaractivity

import com.example.mysterymind.model.dao.ChineseZodiacDao
import com.example.mysterymind.model.dao.ZodiacDao

class ProcessSimilar(private val zodiacDao: ZodiacDao, private val chineseZodiacDao: ChineseZodiacDao) {

    fun determineZodiacCompatibility(sign1: String, sign2: String): Int {
        return if (sign1 == sign2) {
            getSimilarity(sign1)
        } else {
            getCompatibility(sign1)
        } + getCompatibility(sign2)
    }

    private fun getSimilarity(sign: String): Int {
        val similarity = zodiacDao.findZodiacSignCompatibility(sign)
            .find { it.zodiacSign == sign }
            ?.similar ?: 0
        return similarity
    }

    private fun getCompatibility(sign: String): Int {
        val compatibility = zodiacDao.findZodiacSignCompatibility(sign)
            .find { it.zodiacSign == sign }
            ?.compatibility ?: 0
        return compatibility
    }
    fun determineZodiacCompatibility1(sign1: String, sign2: String): Int {
        return if (sign1 == sign2) {
            getSimilarity(sign1)
        } else {
            getCompatibility1(sign1)
        } + getCompatibility1(sign2)
    }

    private fun getSimilarity1(sign: String): Int {
        val similarity = chineseZodiacDao.findZodiacSignCompatibility(sign)
            .find { it.chineseSign == sign }
            ?.similar ?: 0
        return similarity
    }

    private fun getCompatibility1(sign: String): Int {
        val compatibility = chineseZodiacDao.findZodiacSignCompatibility(sign)
            .find { it.chineseSign == sign }
            ?.compatibility ?: 0
        return compatibility
    }
}


