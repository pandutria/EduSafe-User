package com.example.edusfe.util

import android.content.Context
import android.widget.Toast

object support {
    var user_id = 0

    fun msi (context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}