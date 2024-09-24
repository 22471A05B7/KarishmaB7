package com.example.mechservices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Car : AppCompatActivity() {
    lateinit var btn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car)
        btn = findViewById(R.id.btn)

    }
}