package com.example.fragmentado

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val btnTest: ImageButton = view.findViewById(R.id.btn_broadcast)
        val btnPokedb: ImageButton = view.findViewById(R.id.btn_persistencia)

        btnTest.setOnClickListener{
            val intetntBroadcast = Intent(requireActivity(), BroadcastActivity::class.java)
            startActivity(intetntBroadcast)
        }
        btnPokedb.setOnClickListener {
            val intentPersistencia = Intent(requireActivity(), ArmazenarPokemonActivity::class.java)
            startActivity(intentPersistencia)
        }

        return view
    }

}