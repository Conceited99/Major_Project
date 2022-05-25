package com.example.facemaskdetector

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class GoodBye : AppCompatActivity() {
    private lateinit var Username: TextView
    lateinit var tts: TextToSpeech
    val bye: String ="Please take a seat until your Id number is called"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_good_bye)
        tts = TextToSpeech(applicationContext, TextToSpeech.OnInitListener {
            if(it== TextToSpeech.SUCCESS)
            {
                tts.language= Locale.US
                tts.setSpeechRate(1.0f)
                tts.speak(bye.toString(), TextToSpeech.QUEUE_ADD,null)
            }
        })
        val Butt = findViewById<Button>(R.id.Butt);
        Butt.setOnClickListener {
            startActivity(Intent(this,splashscreen::class.java))
            finish()
            }
        }
}
