package com.example.test.domain.use_cases

import android.util.Patterns
import com.example.test.domain.model.ValidationResult

class ValidPassword {

    fun execute(password: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult(
                isSuccessful = false,
                errorMessage = "Password must be at least'"
            )
        }
        val containsLetterAndDigit = password.any { it.isDigit() } && password.any { it.isLetter() }
        if (!containsLetterAndDigit) {
            return ValidationResult(
                isSuccessful = false,
                errorMessage = "Password must contains a letter and a digit"
            )
        }
        return ValidationResult(
            isSuccessful = true
        )
    }
}