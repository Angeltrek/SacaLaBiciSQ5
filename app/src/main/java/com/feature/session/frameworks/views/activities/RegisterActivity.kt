package com.feature.session.frameworks.views.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.feature.session.databinding.ActivityRegisterUserBinding
import com.feature.session.frameworks.viewmodel.RegisterViewModel
import android.widget.DatePicker
import com.feature.session.R
import java.util.Calendar
import java.util.Locale

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterUserBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val calendario = Calendar.getInstance()

        val fecha = DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
            calendario.set(Calendar.YEAR, year)
            calendario.set(Calendar.MONTH, month)
            calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            actualizarFecha(calendario)
        }

        initializeBinding()

        binding.BContinue.setOnClickListener {
            val intent = Intent(this, RegisterContinueActivity::class.java)
            startActivity(intent)
        }

        binding.BBack.setOnClickListener {
            val intent = Intent(this, SessionActivity::class.java)
            startActivity(intent)
        }

        binding.BDate.setOnClickListener {
            DatePickerDialog(
                this,
                fecha,
                calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

    }

    private fun initializeBinding() {
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // Función para obtener actualizar la fecha
    private fun actualizarFecha(calendario: Calendar) {
        val formatoFecha = "dd/MM/yyyy"
        val foramtoSimple = SimpleDateFormat(formatoFecha, Locale.ENGLISH)
        binding.BDate.text = foramtoSimple.format(calendario.time)
    }
}