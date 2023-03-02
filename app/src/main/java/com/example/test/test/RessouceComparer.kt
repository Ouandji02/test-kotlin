package com.example.test.test

import android.content.Context

class RessouceComparer {
    fun isEqualTo(context: Context, resId : Int, string: String) : Boolean {
        return context.getString(resId) == string
    }
}