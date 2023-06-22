package com.example.mysterymind.validation

class Validation {
    private fun isValidDate(date: String): Boolean {
        val pattern = "^\\d{2}(\\d{2})?(\\d{4})?$"
        val regex = Regex(pattern)
        return regex.matches(date)
    }
}