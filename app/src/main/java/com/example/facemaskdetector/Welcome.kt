package com.example.facemaskdetector

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_welcome.*
import java.util.*

class Welcome : AppCompatActivity() {
    private val delay: Long = 500
    private lateinit var username : EditText
    private lateinit var heading: TextView
    private lateinit var id: TextView

    lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        username = findViewById(R.id.Namefeild)
        heading = findViewById(R.id.textView)
        id = findViewById(R.id.textname)
        tts = TextToSpeech(applicationContext, TextToSpeech.OnInitListener {
            if(it==TextToSpeech.SUCCESS)
            {
                tts.language= Locale.US
                tts.setSpeechRate(1.0f)
                tts.speak(heading.text.toString(),TextToSpeech.QUEUE_ADD,null)
                tts.speak(id.text.toString(),TextToSpeech.QUEUE_ADD,null)

            }
        })
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