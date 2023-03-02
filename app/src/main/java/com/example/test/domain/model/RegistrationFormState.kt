package com.example.test.domain.model

data class RegistrationFormState(
    val email: String = "",
    val emailError: String? = null,
    val password : String = "",
    val passwordError: String? = null,
    val repeatPassword : String = "",
    val repeatPasswordError: String? = null
)
