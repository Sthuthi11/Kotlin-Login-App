package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {
    private lateinit var signIn: TextView
    private lateinit var signUp: Button
    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        signIn = findViewById(R.id.signin)
        signUp = findViewById(R.id.buttonSignUp)
        editTextEmail = findViewById(R.id.email) // Changed to match the email EditText id in the XML layout
        editTextPassword = findViewById(R.id.password) // Changed to match the password EditText id in the XML layout

        signIn.setOnClickListener {
            val intent = Intent(this@Register, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        signUp.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(applicationContext, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create user with email and password
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Registration success
                        val user = auth.currentUser
                        Toast.makeText(applicationContext, "Registration successful", Toast.LENGTH_SHORT).show()
                        // Proceed with your desired action, like navigating to another activity
                    } else {
                        // Registration failed
                        Toast.makeText(applicationContext, "Registration failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
