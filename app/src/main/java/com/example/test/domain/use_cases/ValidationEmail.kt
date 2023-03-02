package com.example.test.domain.use_cases

import android.util.Patterns
import com.example.test.domain.model.ValidationResult

class ValidationEmail {

    fun execute(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                isSuccessful = false,
                errorMessage = "Email can't be blank'"
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                isSuccessful = false,
                errorMessage = "That's not a valid email address"
            )
        }
        return ValidationResult(
            isSuccessful = true
        )
    }
}