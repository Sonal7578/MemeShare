package com.example.memeshare

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    private lateinit var memeImageView: ImageView // Declare ImageView variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize memeImageView
        memeImageView = findViewById(R.id.memeImageView)

        // Set padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Load initial meme when activity is created
        loadMeme()
    }

    private fun loadMeme() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.com/gimme"

        // Request a JSON object response from the provided URL.
        val JsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener<JSONObject> { response ->
                // Handle the JSON response here
                val imageUrl = response.getString("url")
                Glide.with(this).load(imageUrl).into(memeImageView)
            },
            Response.ErrorListener {
                // Handle errors here
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            })

        // Add the request to the RequestQueue.
        queue.add(JsonObjectRequest)
    }

    fun shareMeme(view: View) {
        // Implementation for sharing meme
    }

    fun nextMeme(view: View) {
        // Load next meme when button is clicked
        loadMeme()
    }
}
