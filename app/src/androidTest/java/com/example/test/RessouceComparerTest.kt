package com.example.test

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.test.test.RessouceComparer
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RessouceComparerTest {
    private lateinit var resourceComparer : RessouceComparer

    @Before
    fun setUp() {
        resourceComparer = RessouceComparer()
    }

    @Test
    fun stringResourceSameAsGivenString_returnsTrue(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqualTo(context,R.string.app_name, "test")
        assertTrue(result)
    }

    @Test
    fun stringResourceDifferentAsGivenString_returnsFalse(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqualTo(context,R.string.app_name, "hhh")
        assertFalse(result)
    }

}