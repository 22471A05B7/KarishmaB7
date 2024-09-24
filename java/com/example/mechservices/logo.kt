package com.example.mechservices

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView

class logo : AppCompatActivity() {

    companion object {
        const val SPLASH_SCREEN = 5000 // Duration in milliseconds
    }

    private lateinit var topAnim: android.view.animation.Animation
    private lateinit var bottomAnim: android.view.animation.Animation
    private lateinit var image: ImageView
    private lateinit var logo: TextView
    private lateinit var slogan: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo)

        try {
            // Load animations
            topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
            bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle exception, maybe show an error message or fallback animation
        }

        // Hooks
        image = findViewById(R.id.imageView)
        logo = findViewById(R.id.textView3)
        slogan = findViewById(R.id.textView4)

        // Apply animations
        image.startAnimation(topAnim)
        logo.startAnimation(bottomAnim)
        slogan.startAnimation(bottomAnim)

        // Create a new Handler
        val handler = Handler(Looper.getMainLooper())

        // Post a delayed action
        handler.postDelayed({
            // Create an Intent to start MainActivity
            val intent = Intent(this, login::class.java)

            // Start MainActivity
            startActivity(intent)

            // Finish the current activity
            finish()
        }, SPLASH_SCREEN.toLong())
    }
}
