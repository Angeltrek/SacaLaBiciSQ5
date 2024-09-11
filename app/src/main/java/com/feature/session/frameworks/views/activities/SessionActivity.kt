package com.feature.session.frameworks.views.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.feature.session.databinding.ActivitySessionBinding
import com.feature.session.frameworks.viewmodel.LoginViewModel

class SessionActivity: AppCompatActivity() {
    lateinit var binding: ActivitySessionBinding
    private val viewModel: LoginViewModel by viewModels()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            initializeBinding()

            binding.bLogin.setOnClickListener {
//                viewModel.login()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

            binding.bRegister.setOnClickListener {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }

        private fun initializeBinding() {
            binding = ActivitySessionBinding.inflate(layoutInflater)
            setContentView(binding.root)
        }
}