package com.example.fragmentado

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ReferenciaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_referencia, container, false)
    }

    private fun openBrowser(url: String) {
        val webpage: Uri = Uri.parse(url)
        val iUri = Intent(Intent.ACTION_VIEW, webpage)
        startActivity(iUri)
    }
}