package com.example.rosell_loginactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var textView: TextView
    private lateinit var tvWelcomeMessage: TextView

    private val validationLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            textView.visibility = View.GONE
            etUsername.visibility = View.GONE
            etPassword.visibility = View.GONE
            btnLogin.visibility = View.GONE
            tvWelcomeMessage.visibility = View.VISIBLE
                tvWelcomeMessage.text = "Welcome, ${etUsername.text}!"
        } else {
            showLoginErrorDialog()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.button)
        textView = findViewById(R.id.textView)
        tvWelcomeMessage = findViewById(R.id.tvWelcomeMessage)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter your username and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this, ValidationActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("password", password)
            validationLauncher.launch(intent)
        }
    }
    private fun showLoginErrorDialog() {
        AlertDialog.Builder(this)
            .setTitle("Login Failed")
            .setMessage("The username or password you entered is incorrect. Please double-check and try again.")
            .setCancelable(false)
            .setPositiveButton("Try Again") { dialog, _ ->
                etPassword.text.clear()
                etPassword.requestFocus()
                dialog.dismiss()
            }
            .show()
    }
}
