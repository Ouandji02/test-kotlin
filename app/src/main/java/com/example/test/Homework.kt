package com.example.test

object Homework {
    fun fib(n: Int): Long {

        if ((n == 0).or(n == 1)) return n.toLong()
        var a = 0L
        var b = 1L
        var c = 1L

        (2..n).forEach { i ->
            c = a + b
            a = b
            b = c
        }
        return c
    }

    fun checkBraces(string: String): Boolean {
        val openBrace = string.count { it == '(' }
        val closeBrace = string.count { it == ')' }
        return openBrace == closeBrace
    }
}