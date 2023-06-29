package com.example.mysterymind.validation

class Validation {
    fun validateNames(maleName: String, femaleName: String): Boolean {
        val trimmedMaleName = maleName.trim()
        val trimmedFemaleName = femaleName.trim()

        if (trimmedMaleName.isEmpty() || trimmedFemaleName.isEmpty()) {
            return false
        }

        return true
    }
}
