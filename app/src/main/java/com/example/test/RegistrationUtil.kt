package com.example.test

object RegistrationUtil {

    private val existingUsers = listOf("Peter","Carl")

    /**
     * the input is not valid  if...
     * ...the username /password is empty
     * ...the username is already taken
     * ...the confirmed password is not the saame as the real password
     */
    fun validateRegistration(
        username : String,
        password : String,
        confirmedPassword : String
    ) : Boolean {

        if (username.isEmpty() || password.isEmpty()) return  false
        if (existingUsers.contains(username)) return false
        if (confirmedPassword != password) return false
        return true
    }
}
