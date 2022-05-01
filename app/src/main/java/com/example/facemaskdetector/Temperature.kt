package com.example.facemaskdetector

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Temperature : AppCompatActivity() {
    private lateinit var Username: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperature)
        val message = intent.getStringExtra("MESSAGE")
        Username = findViewById<TextView?>(R.id.User).apply {
            text = message
        }
        val Butt = findViewById<Button>(R.id.Butt);
        Butt.setOnClickListener {
                val intent = Intent(this,Vitals::class.java).also {
                    it.putExtra("MESSAGE",message)
                    startActivity(it)
                }
        }
    }
}