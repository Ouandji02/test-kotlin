package com.example.test.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.model.RegistrationFormState
import com.example.test.domain.model.ValidationResult
import com.example.test.domain.use_cases.ValidPassword
import com.example.test.domain.use_cases.ValidRepeatPassword
import com.example.test.domain.use_cases.ValidationEmail
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val validEmail: ValidationEmail = ValidationEmail(),
    private val validRepeatPassword: ValidRepeatPassword = ValidRepeatPassword(),
    private val validpassword : ValidPassword = ValidPassword()
) : ViewModel() {

    var state = MutableStateFlow(RegistrationFormState())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validateEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: RegistrationFormEvent) {
        when (event) {
            is RegistrationFormEvent.EmailChanged -> {
                state.value = state.value.copy(email = event.email)
            }
            is RegistrationFormEvent.PasswordChanged -> {
                state.value = state.value.copy(password = event.password)
            }
            is RegistrationFormEvent.RepeatPasswordChanged -> {
                state.value = state.value.copy(repeatPassword = event.repeatPassword)
            }
            is RegistrationFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validEmail.execute(state.value.email)
        val passwordResult = validpassword.execute(state.value.password)
        val repeatPasswordResult =
            validRepeatPassword.execute(state.value.repeatPassword, state.value.password)
        val hasError = listOf<ValidationResult>(
            emailResult,
            passwordResult,
            repeatPasswordResult
        ).any { !it.isSuccessful }
        if (hasError) {
            state.value = state.value.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatPasswordError = repeatPasswordResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }
}