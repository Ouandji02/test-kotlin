package com.example.test.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ViewModelForm : ViewModel() {
    var userConfirmPassword by mutableStateOf("")
    var visibility by mutableStateOf(false)
    var user by mutableStateOf<User>(User())
    private var _users = MutableStateFlow<List<User>>(listOf())
    val users: StateFlow<List<User>> = _users

    fun changeName(name: String) {
        user = user.copy(name = name)
    }

    fun changeUserPassword(password: String) {
        user = user.copy(password = password)
    }

    fun changeUserConfirmPassword(confirmPassword: String) {
        userConfirmPassword = confirmPassword
    }

    fun changeVisibility() {
        visibility = !visibility
    }

    fun saveUser() {
        if (user.password == userConfirmPassword) {
            _users.value += user.toEncode()
            user = User()
            userConfirmPassword = ""
        } else println("mot de passe incorrect")
        println(_users.value)
    }
}


