package com.example.understandinglogcat

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Sign_in_activity : AppCompatActivity() {
    lateinit var databaseReferences: DatabaseReference
    companion object{
        const val KEY1 ="com.example.understandinglogcat.Sign_in_activity.mail"
        const val KEY2 ="com.example.understandinglogcat.Sign_in_activity.name"
        const val KEY3 ="com.example.understandinglogcat.Sign_in_activity.username"
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signINButton = findViewById<Button>(R.id.btnsign)
        val usernamme = findViewById<TextInputEditText>(R.id.Usernameedittext)

        signINButton.setOnClickListener {
            //take ref till node "user"
            val userNameString = usernamme.text.toString()
            if(userNameString.isNotEmpty()){
                readData(userNameString)

            }
            else {
                Toast.makeText(this, "Please Enter User Name ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(userNameString: String) {

        databaseReferences = FirebaseDatabase.getInstance().getReference("Users")
        databaseReferences.child(userNameString).get().addOnSuccessListener {

            if(it.exists()){
                //welcome user in your app,
                val email = it.child("email").value
                val name =it.child("name").value
                val username= it.child("userNameString").value

                val intentWelcome = Intent(this,WelcomeActivity::class.java)
                intentWelcome.putExtra(KEY1,email.toString())
                intentWelcome.putExtra(KEY2, name.toString())
                intentWelcome.putExtra(KEY3 , userNameString.toString())
                startActivity(intentWelcome)
            }
            else{
                Toast.makeText(this, "user does not exist", Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {
            Toast.makeText(this, "Failed ", Toast.LENGTH_SHORT).show()
        }



    }
}