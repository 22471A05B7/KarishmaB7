package com.example.mechservices

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import org.json.JSONException
import org.json.JSONObject

class Bus : AppCompatActivity() {
    private lateinit var mechanicsContainer: CardView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Fix: Initialize mechanicsContainer as CardView
        mechanicsContainer = findViewById(R.id.mechanicContainer)

        val lat = 16.198602
        val lon = 80.057747

        NetworkClient.fetchMechanics(lat, lon) { jsonData ->
            runOnUiThread {
                jsonData?.let { processJsonData(it) }
            }
        }
    }

    private fun processJsonData(jsonData: String) {
        try {
            val jsonObject = JSONObject(jsonData)
            val elements = jsonObject.getJSONArray("elements")

            for (i in 0 until elements.length()) {
                val element = elements.getJSONObject(i)
                val tags = element.getJSONObject("tags")
                val name = tags.optString("name", "Unknown Shop")
                val phone = tags.optString("phone", "No Contact Info")
                val shop = tags.optString("shop", "Services not listed")
                val opening_hours = tags.optString("opening_hours", "Not Available")

                if (phone != "No Contact Info" && name == "Bismilla cell point") {
                    addMechanicToLayout(name, phone, shop, opening_hours)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun addMechanicToLayout(name: String, phone: String, shop: String, openingHours: String) {
        // Inflate the correct mechanic layout
        val mechanicView = layoutInflater.inflate(R.layout.activity_main2, null)

        val shopNameTextView = mechanicView.findViewById<TextView>(R.id.textShopName)
        val phoneButton = mechanicView.findViewById<Button>(R.id.btnCall)
        val servicesTextView = mechanicView.findViewById<TextView>(R.id.textServices)
        val openingHoursTextView = mechanicView.findViewById<TextView>(R.id.textOpeningHours)

        shopNameTextView.text = "Shop Name: $name"
        servicesTextView.text = "Services: $shop"
        openingHoursTextView.text = "Opening Hours: $openingHours"
        phoneButton.text = "Call: $phone"

        phoneButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phone")
            startActivity(intent)
        }

        mechanicsContainer.addView(mechanicView)
    }
}
