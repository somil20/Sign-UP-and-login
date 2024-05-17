package com.example.understandinglogcat

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {


    lateinit var database : DatabaseReference
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondactivity)



        val signButton = findViewById<Button>(R.id.button)
        val  etMail = findViewById<TextInputEditText>(R.id.etMail)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val username = findViewById<TextInputEditText>(R.id.etUsername)



        signButton.setOnClickListener {

            val name  = etName.text.toString()
            val mail = etMail.text.toString()
            val username = username.text.toString()
            val password = etPassword.text.toString()

            val user = user(name, mail ,password, username)

            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(username).setValue(user).addOnSuccessListener {

                etMail.text?.clear()
                etName.text?.clear()
                etPassword.text?.clear()

                Toast.makeText(this,"User Registered", Toast.LENGTH_SHORT ).show()
            }.addOnSuccessListener {
                Toast.makeText(this,"Failed", Toast.LENGTH_SHORT ).show()

            }
        }

        val signIntent = findViewById<TextView>(R.id.tvsignin)

        signIntent.setOnClickListener {
            val openSignINActivity = Intent(this,Sign_in_activity::class.java)
            startActivity(openSignINActivity)
        }
    }
}