package com.example.mechservices


import kotlinx.serialization.Serializable

@Serializable
data class Mechanic(
    val name: String,
    val phone: String,
    val shop: String,
    val opening_hours:String
)