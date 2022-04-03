package com.example.facemaskdetector

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private val delay: Long = 2000

class Temperature : AppCompatActivity() {
    private lateinit var Username: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperature)
        Username = findViewById(R.id.User)
        val user = intent.getStringExtra("Username")
        val Butt = findViewById<Button>(R.id.Butt);
        Butt.setOnClickListener {
            Handler().postDelayed({
                startActivity(Intent(this,Vitals::class.java))
                finish()
            },delay)
        }
    }
}