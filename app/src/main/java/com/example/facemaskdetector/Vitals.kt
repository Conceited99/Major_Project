package com.example.facemaskdetector

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
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
    var count = 0
    var bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    var hco5 = bluetoothAdapter.getRemoteDevice("00:21:08:01:0B:F1")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vitals)
        val message = intent.getStringExtra("MESSAGE")
        Username = findViewById<TextView?>(R.id.User).apply {
            text = message
        }
        data = findViewById(R.id.Data)
        val t = Thread2()
        t.start()
        val Butt = findViewById<Button>(R.id.Butt)
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
                data.text = tempMsg
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
                    if (ContextCompat.checkSelfPermission(this@Vitals, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
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
                                try {
                                    Thread.sleep(1000)
                                    bytes = inputStream.read(buffer)
                                    count++
                                    handler.obtainMessage(STATE_MESSAGE_RECIEVED, bytes, -1, buffer)
                                        .sendToTarget()
                                    sleep(500)
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