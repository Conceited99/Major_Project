package com.example.facemaskdetector

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Covid_Activity : AppCompatActivity() {
    private lateinit var Username: TextView
    lateinit var tts : TextToSpeech
    val covid: String ="Please fill out the covid risk assessment form"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid)
        val message = intent.getStringExtra("MESSAGE")
        Username = findViewById<TextView?>(R.id.User).apply {
            text = message
        }
        tts = TextToSpeech(applicationContext, TextToSpeech.OnInitListener {
            if(it==TextToSpeech.SUCCESS)
            {
                tts.language= Locale.US
                tts.setSpeechRate(1.0f)
                tts.speak(covid.toString(),TextToSpeech.QUEUE_ADD,null)

            }
        })
        val Butt = findViewById<Button>(R.id.Butt);
        Butt.setOnClickListener {
                val intent = Intent(this,SendData::class.java).also {
                    it.putExtra("MESSAGE",message)
                    startActivity(it)
                }
        }
    }
}