package com.example.test.domain.use_cases

import com.example.test.domain.model.ValidationResult

class ValidRepeatPassword {
    fun execute(repeatPassword: String, password: String): ValidationResult {
        if (repeatPassword != password) return ValidationResult(
            isSuccessful = false,
            errorMessage = "Repeat password is incorrect"
        )
        return ValidationResult(
            isSuccessful = true
        )
    }
}