package com.example.mechservices

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mechservices.databinding.ActivityPlanner2Binding

class Planner2 : AppCompatActivity() {

    private lateinit var binding: ActivityPlanner2Binding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_planner2)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("mech_pref", MODE_PRIVATE)
        sharedPreferencesEditor = sharedPreferences.edit()

        // Set click listener for the register button
        binding.regis.setOnClickListener {
            val name = binding.name.text.toString()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val confirmPassword = binding.cpas.text.toString()

            if (isValidInput(name, email, password, confirmPassword)) {
                // Save user information to SharedPreferences
                sharedPreferencesEditor.putString("myname", name)
                sharedPreferencesEditor.putString("email", email)
                sharedPreferencesEditor.putString("pwd", password)
                sharedPreferencesEditor.apply()

                // Show success message
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()

                // Proceed to the login activity
                val intent = Intent(this, login::class.java)
                startActivity(intent)
                finish() // Close the registration activity
            }
        }
    }

    private fun isValidInput(name: String, email: String, password: String, confirmPassword: String): Boolean {
        // Validation logic
        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            return false
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
            return false
        }
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        if (!email.matches(emailRegex)) {
            Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show()
            return false
        }
        if (confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please confirm your password", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return false
        }
        val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d).{6,}$")
        if (!password.matches(passwordRegex)) {
            Toast.makeText(this, "Password must be at least 6 characters long and contain at least one letter and one number.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}
