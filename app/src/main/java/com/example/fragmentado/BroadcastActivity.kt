package com.example.fragmentado

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BroadcastActivity : AppCompatActivity() {
    private val airPlaneModeReceiver = AirPlaneModeReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(airPlaneModeReceiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
        setContentView(R.layout.activity_broadcast)

        val btnBack: ImageButton = findViewById(R.id.btn_backHome)
        val btnNext: ImageButton = findViewById(R.id.btn_next_item)
        val btnUpdate: Button = findViewById(R.id.btn_atualizar)
        val texto: EditText = findViewById(R.id.txt_putSend)
        val textoTemp : TextView = findViewById(R.id.txt_temporario)

        btnBack.setOnClickListener {
            finish()
        }
        btnUpdate.setOnClickListener {
            val getText = texto.text.toString()
            textoTemp.text = getText
        }
        btnNext.setOnClickListener{
            val intentNewScreen = Intent(this, PutExtraActivity::class.java).apply {
                putExtra("texto", textoTemp.text)
            }
            startActivity(intentNewScreen)
        }

        val intent = Intent(this, MusicService::class.java)
        startService(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(airPlaneModeReceiver)
    }
}