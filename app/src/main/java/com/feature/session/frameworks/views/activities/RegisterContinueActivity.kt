package com.feature.session.frameworks.views.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.feature.session.databinding.ActivityRegisterUserContinueBinding
import com.feature.session.frameworks.viewmodel.RegisterContinueViewModel

class RegisterContinueActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterUserContinueBinding
    private val viewModel: RegisterContinueViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()

//        binding.bFinalizar.setOnClickListener {
//            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)
//        }

        binding.bBack.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initializeBinding() {
        binding = ActivityRegisterUserContinueBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}