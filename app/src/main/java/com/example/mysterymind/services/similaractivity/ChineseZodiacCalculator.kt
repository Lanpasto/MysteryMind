package com.example.mysterymind.services.similaractivity


class ChineseZodiacCalculator {
    private val zodiacSigns = listOf(
        "Rat", "Ox", "Tiger", "Rabbit", "Dragon", "Snake",
        "Horse", "Goat", "Monkey", "Rooster", "Dog", "Pig"
    )

    fun calculateZodiacSign(date: String): String {
        val year = extractYear(date)
        val zodiacIndex = year % 12
        return zodiacSigns[zodiacIndex]
    }


    private fun extractYear(date: String): Int {
        val parts = date.split(".")
        return parts.getOrNull(2)?.toIntOrNull() ?: 0
    }
}
