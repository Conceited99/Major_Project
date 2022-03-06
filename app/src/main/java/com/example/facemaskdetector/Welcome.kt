package com.example.facemaskdetector

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class Welcome : AppCompatActivity() {
    private val delay: Long = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        Butt.setOnClickListener{
            Handler().postDelayed({
                startActivity(Intent(this,MainActivity::class.java))
                finish()

            },delay)
        }
    }
}