package com.example.test.presentation

sealed class RegistrationFormEvent {
    data class EmailChanged(val email: String) : RegistrationFormEvent()
    data class PasswordChanged(val password: String) : RegistrationFormEvent()
    data class RepeatPasswordChanged(val repeatPassword: String) : RegistrationFormEvent()
    object Submit : RegistrationFormEvent()
}