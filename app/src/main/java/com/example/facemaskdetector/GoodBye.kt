package com.example.facemaskdetector

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

private val delay : Long = 5000
class GoodBye : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_good_bye)
        Handler().postDelayed({
            startActivity(Intent(this,splashscreen::class.java))
            finish()

        },delay)
    }
}