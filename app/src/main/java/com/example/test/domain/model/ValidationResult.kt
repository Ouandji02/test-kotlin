package com.example.test.domain.model

data class ValidationResult(
    val isSuccessful: Boolean,
    val errorMessage: String? = null
)
