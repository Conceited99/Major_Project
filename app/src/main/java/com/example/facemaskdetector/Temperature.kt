package com.example.facemaskdetector

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Temperature : AppCompatActivity() {
    private lateinit var Username: TextView
    lateinit var tts: TextToSpeech
    val tmp: String ="Please stand infront of the robot to take temperature"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperature)
        val message = intent.getStringExtra("MESSAGE")
        Username = findViewById<TextView?>(R.id.User).apply {
            text = message
            tts = TextToSpeech(applicationContext, TextToSpeech.OnInitListener {
                if(it== TextToSpeech.SUCCESS)
                {
                    tts.language= Locale.US
                    tts.setSpeechRate(1.0f)
                    tts.speak(tmp.toString(), TextToSpeech.QUEUE_ADD,null)

                }
            })
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