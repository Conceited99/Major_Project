package com.example.facemaskdetector

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class Welcome : AppCompatActivity() {
    private val delay: Long = 500
    private lateinit var username : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        username = findViewById(R.id.Namefeild)
        Butt.setOnClickListener{
            if(TextUtils.isEmpty(username.text))
            {
                Toast.makeText(this, "PLEASE ENTER YOUR NAME", Toast.LENGTH_LONG).show()
            }
             else {
                Handler().postDelayed({
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                },delay)
            }
        }
    }
}