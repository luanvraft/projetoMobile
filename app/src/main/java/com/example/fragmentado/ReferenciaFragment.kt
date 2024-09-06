package com.example.fragmentado

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class ReferenciaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_referencia, container, false)

        val btnAPI: Button = view.findViewById(R.id.btn_API)
        val btnSensor: Button = view.findViewById(R.id.btn_sensor)
        val btnSTR: Button = view.findViewById(R.id.btn_str)
        val btnNavMenu: Button = view.findViewById(R.id.btn_menu)
        val btnGemini: Button = view.findViewById(R.id.btn_gemini)
        val btnBcast: Button = view.findViewById(R.id.btn_bcast)

        btnAPI.setOnClickListener {
            openBrowser(
                "https://www.youtube.com/watch?v=qrxNlz1hCik"
            )
        }
        btnSensor.setOnClickListener {
            openBrowser(
                "https://www.luiztools.com.br/post/" +
                        "como-criar-um-aplicativo-android-com-gps/"
            )
        }
        btnSTR.setOnClickListener {
            openBrowser(
                "https://stackoverflow.com/questions/3904579/" +
                        "how-to-capitalize-the-first-letter-of-a-string-in-java"
            )
        }
        btnNavMenu.setOnClickListener {
            openBrowser(
                "https://www.youtube.com/watch?v=zBETkYi9Z4E"
            )
        }
        btnGemini.setOnClickListener {
            openBrowser(
                "https://gemini.google.com/app"
            )
        }
        btnBcast.setOnClickListener {
            openBrowser(
                "https://youtu.be/wkrAVh7Wgio?si=-QrxOh2seh4Q2h7s"
            )
        }

        return view
    }


    private fun openBrowser(url: String) {
        val webpage: Uri = Uri.parse(url)
        val iUri = Intent(Intent.ACTION_VIEW, webpage)
        startActivity(iUri)
    }
}