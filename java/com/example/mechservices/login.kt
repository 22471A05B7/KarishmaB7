package com.example.mechservices

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class login : AppCompatActivity() {

    private lateinit var user: TextInputEditText
    private lateinit var passwo: TextInputEditText
    private lateinit var login: Button
    private lateinit var regi: Button
    private lateinit var sp: SharedPreferences
    private lateinit var spe: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sp = getSharedPreferences("mech_pref", MODE_PRIVATE)

        // Initialize the views from the layout
        user = findViewById(R.id.name)
        passwo = findViewById(R.id.password)
        login = findViewById(R.id.login)
        regi = findViewById(R.id.signup)

        // Handle the login button click
        login.setOnClickListener {
            val username = user.text.toString()
            val password = passwo.text.toString()

            if (username.isEmpty()) {
                Toast.makeText(this, "Please enter the username", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Please enter the password", Toast.LENGTH_SHORT).show()
            } else {
                val storedName = sp.getString("myname", "").toString()
                val storedPassword = sp.getString("pwd", "").toString()

                if (username == storedName && password == storedPassword) {
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                    spe = sp.edit()
                    spe.putBoolean("isLoggedIn", true)
                    spe.apply()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Invalid Login", Toast.LENGTH_SHORT).show()
                }
            }
        }
        regi.setOnClickListener {
            val signUpIntent = Intent(this, Planner2::class.java)
            startActivity(signUpIntent)
        }
    }
}
