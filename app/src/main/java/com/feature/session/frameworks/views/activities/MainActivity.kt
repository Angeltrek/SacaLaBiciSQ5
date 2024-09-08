package com.feature.session.frameworks.views.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.feature.session.R
import com.feature.session.frameworks.viewmodel.MainViewModel
import com.feature.session.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()

        val loadingSpinner = findViewById<ProgressBar>(R.id.loading_spinner)
        loadingSpinner.visibility = View.VISIBLE

        Handler(Looper.getMainLooper()).postDelayed({
            loadingSpinner.visibility = View.GONE
            val intent = Intent(this, SessionActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}