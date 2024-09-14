package com.feature.session.frameworks.views.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.feature.session.databinding.ActivityLoginBinding
import com.feature.session.frameworks.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginActivity: AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        initializeBinding()

        binding.BSession.setOnClickListener {
            val email = binding.TILEmail.editText?.text.toString()
            val password = binding.TILPassword.editText?.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Login successful
                            val intent = Intent(this, MainActivity::class.java) // Replace MainActivity with your desired activity
                            startActivity(intent)
                            finish() // Optional: Finish LoginActivity to prevent going back
                        } else {
                            Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.BGoogle.setOnClickListener {
            var signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(
                    BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId(getString(R.string.your_web_client_id))
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(true)
                        .build()
                )
                .build()
        }

        binding.BBack.setOnClickListener {
            val intent = Intent(this, SessionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initializeBinding() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}