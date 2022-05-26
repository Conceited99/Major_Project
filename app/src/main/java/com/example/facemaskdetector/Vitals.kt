package com.example.facemaskdetector

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.IOException
import java.util.*


class Vitals : AppCompatActivity() {
    private lateinit var Username: TextView
    private lateinit var data: TextView
    private lateinit var Butt: Button
    lateinit var vitals :TextView
    lateinit var tts: TextToSpeech
    var count = 0
    var bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    var hco5 = bluetoothAdapter.getRemoteDevice("00:21:08:01:0B:F1")
    val vi: String ="When the scan is complete select the next button"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vitals)
        val message = intent.getStringExtra("MESSAGE")
        Username = findViewById<TextView?>(R.id.User).apply {
            text = message
        }
        tts = TextToSpeech(applicationContext, TextToSpeech.OnInitListener {
            if(it==TextToSpeech.SUCCESS)
            {
                tts.language= Locale.US
                tts.setSpeechRate(1.0f)
                tts.speak(vi.toString(),TextToSpeech.QUEUE_ADD,null)
            }
        })
        data = findViewById(R.id.Data)
        val t = Thread2()
        t.start()
        Butt = findViewById<Button>(R.id.Butt)
        Butt.setOnClickListener{
            val intent = Intent(this,Covid_Activity::class.java).also {
                it.putExtra("MESSAGE",message)
                startActivity(it)
            }
        }
    }
    var handler = Handler { msg ->
        when (msg.what) {
            Vitals.STATE_MESSAGE_RECIEVED -> {
                val readBuff = msg.obj as ByteArray
                val tempMsg = String(readBuff, 0, msg.arg1)
                val d = tempMsg.split("\r\n")[0]
                println(tempMsg)
                if (d.contains('$'))
                {
                    val tempMsg2 = d.split('$')
                    data.text = String.Companion.format("BPM:%s SpO2: %s", tempMsg2[0],tempMsg2[1])
                }
               // val size = d.size
                //var temp = ""
               // println(tempMsg)
               // if(size >1 ){
                   // temp =d[size-2]
                //}else {
                  //  temp =d[0]
                //}


            }
            Vitals.STATE_CONNECTION_FAILED -> {}
        }
        return@Handler true
    }

    private inner class Thread2 : Thread() {
        override fun run() {
            var socket: BluetoothSocket? = null
            do {
                try {
                    if (ContextCompat.checkSelfPermission(this@Vitals, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager
                            .PERMISSION_DENIED) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                            ActivityCompat.requestPermissions(
                                this@Vitals,
                                arrayOf(Manifest.permission.BLUETOOTH_CONNECT),
                                2
                            )
                            return
                        }
                        socket = hco5.createRfcommSocketToServiceRecord(mUUID)
                        socket.connect()
                        val inputStream = socket.inputStream
                        val buffer = ByteArray(1024)
                        var bytes: Int
                        var term = true
                        while (term) {
                            for (i in 0..29) {
                                if(i<=3)
                                {
                                    Butt.setText("10%");
                                }
                               else if(i>3 && i<=6)
                                {
                                    Butt.setText("20%");
                                }
                               else if(i>6 && i<=9)
                                {
                                    Butt.setText("30%");
                                }
                                else if(i>9 && i<=12)
                                {
                                    Butt.setText("40%");
                                }
                               else if(i>12 && i<=15)
                                {
                                    Butt.setText("50%");
                                }
                                else if(i>15 && i<=18)
                                {
                                    Butt.setText("60%");
                                }
                                else if(i>18 && i<=21)
                                {
                                    Butt.setText("70%");
                                }
                               else if(i>21 && i<=24)
                                {
                                    Butt.setText("80%");
                                }
                                else if(i>24 && i<=28)
                                {
                                    Butt.setText("90%");
                                }
                                else{
                                    Butt.setText("Next")
                                }
                                try {
                                 // Thread.sleep(1000)
                                    bytes = inputStream.read(buffer)
                                    count++
                                    handler.obtainMessage(STATE_MESSAGE_RECIEVED, bytes, -1, buffer)
                                        .sendToTarget()
                                    //sleep(500)
                                } catch (e: IOException) {
                                    e.printStackTrace()
                                } catch (e: InterruptedException) {
                                    e.printStackTrace()
                                }
                            }
                            term = false
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } while (!socket!!.isConnected)
            try {
                socket.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    companion object {
        private val mUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        const val STATE_MESSAGE_RECIEVED = 1
        const val STATE_CONNECTION_FAILED = 2
    }
}