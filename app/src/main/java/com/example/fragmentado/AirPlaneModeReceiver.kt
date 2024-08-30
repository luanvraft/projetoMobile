package com.example.fragmentado

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast

class AirPlaneModeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (isAirplaneModeOn(context.applicationContext)) {
            Toast.makeText(context, "Modo avião está ligado!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Modo avião está desligado!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isAirplaneModeOn(context: Context): Boolean {
        return Settings.System.getInt(
            context.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON,
            0
        ) != 0
    }
}