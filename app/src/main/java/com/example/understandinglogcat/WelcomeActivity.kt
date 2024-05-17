package com.example.understandinglogcat

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WelcomeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val name = intent.getStringExtra(Sign_in_activity.KEY2)
        val mail = intent.getStringExtra(Sign_in_activity.KEY1)
        val username = intent.getStringExtra(Sign_in_activity.KEY3)

        val welcomeText = findViewById<TextView>(R.id.tvWelcome)
        val mailText = findViewById<TextView>(R.id.tvemail)
        val idText = findViewById<TextView>(R.id.tvusername)

        welcomeText.text = "Welcome $name"
        mailText.text = "Mail : $mail"
        idText.text = "Username : $username"

    }
}