package com.example.facemaskdetector

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Covid_Activity : AppCompatActivity() {
    private lateinit var Username: TextView
    lateinit var tts : TextToSpeech
    val covid: String ="Please fill out the covid risk assessment form"
    lateinit var yes: Button
    lateinit var no : Button
    lateinit var score : TextView
    lateinit var question: TextView
    var mQuestions =  Questions()
    var mScore : Int = 0
    var mQuestionsLength : Int = mQuestions.mQuestions.size
    private val delay: Long = 5000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid)
        val message = intent.getStringExtra("MESSAGE")
        Username = findViewById<TextView?>(R.id.username).apply {
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
        var r: Random = Random()
        yes = findViewById(R.id.yes)
        no = findViewById(R.id.no)
        score = findViewById(R.id.textView14)
        question = findViewById(R.id.textView15)
        score.setText("Score:"+ mScore)
        updateQuestion(r.nextInt(mQuestionsLength))
        yes.setOnClickListener{
            if(mScore!= 12)
            {
                mScore++
                score.setText("Score:"+ mScore)
                updateQuestion(r.nextInt(mQuestionsLength))
            }
            else
            {
                nextActivity()
            }
        }
        no.setOnClickListener{
            if(mScore!= 12)
            {
                mScore++
                score.setText("Score:"+ mScore)
                updateQuestion(r.nextInt(mQuestionsLength))
            }
            else
            {
                nextActivity()
            }
        }

    }
    fun updateQuestion(num: Int)
    {
        question.setText(mQuestions.getQuestions(num))
        yes.setText(mQuestions.getChoices1(num))
        no.setText(mQuestions.getChoices2(num))

    }
    fun nextActivity()
    {
        Toast.makeText(this, "Processing please wait ....", Toast.LENGTH_LONG).show()
        Handler().postDelayed({
            startActivity(Intent(this,SendData::class.java))
            finish()
        },delay)
    }
}