package com.example.mechservices
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

object NetworkClient {
    private val client = OkHttpClient()
    fun fetchMechanics(lat: Double, lon: Double, callback: (String?) -> Unit) {
        val url = "https://overpass-api.de/api/interpreter?data=[out:json];node[\"shop\"~\"repair\"](around:80000,$lat,$lon);out body;"
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                callback(null)
            }
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                callback(response.body?.string())
            }
        })
    }
}



