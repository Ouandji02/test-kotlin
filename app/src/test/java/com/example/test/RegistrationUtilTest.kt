package com.example.test

import com.example.test.test.RegistrationUtil
import org.junit.Assert
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `empty username returns false`() {
        val result = RegistrationUtil.validateRegistration(
            "",
            "123",
            "123"
        )
    }

    @Test
    fun `valid username and correctly repeated password returns true`(){
        val result = RegistrationUtil.validateRegistration(
            "Thierry",
            "123",
            "123"
        )
        Assert.assertTrue(result)
    }

    @Test
    fun `username already exist returns false`(){
        val result = RegistrationUtil.validateRegistration(
            "Carl",
            "123",
            "123"
        )
        Assert.assertFalse(result)
    }

    @Test
    fun `empty password returns false`(){
        val result = RegistrationUtil.validateRegistration(
            "Thierry",
            "",
            ""
        )
        Assert.assertFalse(result)
    }

    @Test
    fun `confirmation password correct returns false`(){
        val result = RegistrationUtil.validateRegistration(
            "Thierry",
            "123",
            "124"
        )
        Assert.assertFalse(result)
    }

}