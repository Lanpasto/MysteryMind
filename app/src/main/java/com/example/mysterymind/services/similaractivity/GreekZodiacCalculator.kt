package com.example.mysterymind.services.similaractivity

class GreekZodiacCalculator {
    private val zodiacSigns = listOf(
        "Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo",
        "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces"
    )

    fun calculateZodiacSign(date: String): String {
        val month = extractMonth(date)
        val day = extractDay(date)
        val zodiacIndex = calculateZodiacIndex(month, day)
        return zodiacSigns[zodiacIndex]
    }

    private fun extractMonth(date: String): Int {
        val parts = date.split(".")
        return parts.getOrNull(1)?.toIntOrNull() ?: 0
    }

    private fun extractDay(date: String): Int {
        val parts = date.split(".")
        return parts.getOrNull(0)?.toIntOrNull() ?: 0
    }

    private fun calculateZodiacIndex(month: Int, day: Int): Int {
        val dates = listOf(
            Pair(20, 18), Pair(19, 20), Pair(21, 19), Pair(20, 20), Pair(21, 20), Pair(21, 21),
            Pair(22, 22), Pair(23, 22), Pair(23, 23), Pair(23, 22), Pair(22, 21), Pair(21, 20)
        )

        for ((index, dateRange) in dates.withIndex()) {
            val (startDay, endDay) = dateRange
            if ((month == index + 1 && day >= startDay) || (month == index + 2 && day <= endDay)) {
                return index
            }
        }

        return 11 // Pisces (default)
    }

}
