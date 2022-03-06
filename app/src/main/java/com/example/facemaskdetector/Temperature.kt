package com.example.facemaskdetector

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

private val delay: Long = 5000
class Temperature : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperature)
        Handler().postDelayed({
            startActivity(Intent(this,Vitals::class.java))
            finish()

        },delay)
    }
}