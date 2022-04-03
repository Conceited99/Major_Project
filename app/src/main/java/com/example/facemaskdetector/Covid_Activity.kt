package com.example.facemaskdetector

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

private val delay : Long = 2000
class Covid_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid)
        val Butt = findViewById<Button>(R.id.Butt);
        Butt.setOnClickListener {
            Handler().postDelayed({
                startActivity(Intent(this,GoodBye::class.java))
                finish()

            },delay)
        }
    }
}