package com.example.mysterymind.services.analyticActivity

import com.example.mysterymind.model.dao.NameDao
import com.example.mysterymind.model.entity.NameSignCompatibility
import com.example.mysterymind.model.dao.ZodiacDao
import com.example.mysterymind.model.entity.ZodiacSignCompatibility

class FemaleInfoProcessor(private val nameDao: NameDao, private val zodiacDao: ZodiacDao) {
     fun getCompatibility(name: String): NameSignCompatibility? {
        return nameDao.findByName(name)
    }

     fun getZodiacSignCompatibility(zodiacSign: String): List<ZodiacSignCompatibility> {
        return zodiacDao.findZodiacSignCompatibility(zodiacSign)
    }


}/* fun calculateCompatibility(name: String): Float {
        val vowelCount = countVowels(name)
        val letterCount = name.length

        var compatibility = 0.0f

        if (vowelCount == 0) {
            // Якщо немає голосних, сумісність буде 0%
            compatibility = 0.0f
        } else if (vowelCount <= 3) {
            // Якщо голосних до 3, сумісність буде 50%
            compatibility = 50.0f
        } else if (vowelCount == 4 || vowelCount == 5) {
            // Якщо голосних 4 або 5, сумісність буде 40%
            compatibility = 40.0f
        } else if (vowelCount >= 6) {
            // Якщо голосних 6 або більше, сумісність буде 35%
            compatibility = 35.0f
        }

        if (letterCount <= 6) {
            // Якщо кількість букв до 6, збільшуємо сумісність на 85%
            compatibility += 50.0f
        } else {
            // Якщо кількість букв більше 6, зменшуємо сумісність за кожну букву
            val extraLetters = letterCount - 6
            compatibility -= extraLetters * 5.0f
        }

        // Перевірка, щоб сумісність не перевищувала 100%
        if (compatibility > 100.0f) {
            compatibility = 100.0f
        }

        return compatibility
    }
    private fun countVowels(name: String): Int {
        val vowels = listOf('a', 'e', 'i', 'o', 'u')
        var count = 0
        for (letter in name.toLowerCase()) {
            if (letter in vowels) {
                count++
            }
        }
        return count
    }*/