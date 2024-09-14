package com.feature.session.frameworks.views.activities

import android.app.DatePickerDialog
import android.app.ProgressDialog
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
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.feature.session.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database
import com.google.firebase.firestore.auth.User
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
            val email = binding.TILEmail.editText?.text.toString()
            val username = binding.TILUsername.editText?.text.toString()
            val birthday = binding.BDate.text.toString()

            if (isValidEmail(email) && isValidUsername(username) && isValidBirthday(birthday)) {
                val intent = Intent(this, RegisterContinueActivity::class.java)
                intent.putExtra("email", email)
                intent.putExtra("username", username)
                intent.putExtra("birthday", birthday)
                startActivity(intent)
            } else {
                if (!isValidEmail(email)) {
                    binding.TILEmail.error = "Invalid email"
                }
                if (!isValidUsername(username)) {
                    binding.TILUsername.error = "Invalid username"
                }
                if (!isValidBirthday(birthday)) {
                    // Show error message for invalid birthday (e.g., Toast or Snackbar)
                    Toast.makeText(this, "Please select your birthday", Toast.LENGTH_SHORT).show()
                }
            }
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

    private fun isValidBirthday(birthday: String): Boolean {
        return birthday != getString(R.string.TDate) // Assuming "Select Date" is the default text
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidUsername(username: String): Boolean {
        // Example: Check if username is not empty and meets certain criteria
        return username.isNotEmpty() && username.length >= 3
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