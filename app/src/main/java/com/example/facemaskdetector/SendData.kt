package com.example.facemaskdetector

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class SendData : AppCompatActivity() {
    private lateinit var Username: TextView
    private val delay: Long = 5000
    lateinit var tts: TextToSpeech
    val send: String ="Sending your data to the nurse, please wait"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_data)
        tts = TextToSpeech(applicationContext, TextToSpeech.OnInitListener {
            if(it== TextToSpeech.SUCCESS)
            {
                tts.language= Locale.US
                tts.setSpeechRate(1.0f)
                tts.speak(send.toString(), TextToSpeech.QUEUE_ADD,null)

            }
        })
        Handler().postDelayed({
            startActivity(Intent(this,GoodBye::class.java))
            finish()
        },delay)
    }

}