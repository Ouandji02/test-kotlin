package com.example.test

import org.junit.Assert.*
import org.junit.Test

class HomeworkTest {

    @Test
    fun `bad brace returns false`(){
        val result = Homework.checkBraces("(a*b))")
        assertFalse(result)
    }

    @Test
    fun `correct brace returns true`(){
        val result = Homework.checkBraces("(a*b)+(c*d)")
        assertTrue(result)
    }

    @Test
    fun `response1 fibonacci should returns false`(){
        val result = Homework.fib(1)
        assertFalse(result == 2L)
    }

    @Test
    fun `response2 fibonacci should returns true`(){
        val result = Homework.fib(4)
        assertTrue(result == 3L)
    }

}