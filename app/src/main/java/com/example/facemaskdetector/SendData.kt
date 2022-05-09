package com.example.facemaskdetector

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SendData : AppCompatActivity() {
    private lateinit var Username: TextView
    private val delay: Long = 5000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_data)
        Handler().postDelayed({
            startActivity(Intent(this,GoodBye::class.java))
            finish()
        },delay)
    }

}