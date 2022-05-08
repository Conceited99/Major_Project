package com.example.facemaskdetector

import android.content.Intent
import android.os.Bundle
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
            if(TextUtils.isEmpty(username.text) || username.length() != 7)
            {
                Toast.makeText(this, "PLEASE DOUBLE CHECK ID#", Toast.LENGTH_LONG).show()
            }
             else {
                 val message = username.text.toString()
                   val intent = Intent(this,Temperature::class.java).also {
                       it.putExtra("MESSAGE",message)
                       startActivity(it)

                   }
            }
        }
    }
}