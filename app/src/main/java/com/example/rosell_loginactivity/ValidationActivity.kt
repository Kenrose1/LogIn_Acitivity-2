package com.example.rosell_loginactivity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ValidationActivity : Activity() {

    companion object {
        const val ADMIN_USERNAME = "admin"
        const val ADMIN_PASSWORD = "password"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val username = intent.getStringExtra("username") ?: ""
        val password = intent.getStringExtra("password") ?: ""

        if (username == ADMIN_USERNAME && password == ADMIN_PASSWORD) {
            setResult(Activity.RESULT_OK)
        } else {
            setResult(Activity.RESULT_CANCELED)
        }
        finish()
    }
}