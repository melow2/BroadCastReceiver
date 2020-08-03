package com.khs.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.TextView
import android.widget.Toast

class BroadCastReceiverEx() : BroadcastReceiver() {

    companion object {
        const val TAG = "BroadCastReceiverEx"
        const val STRING_POWER_CONNECTED = "android.intent.action.ACTION_POWER_CONNECTED"
        const val STRING_POWER_DISCONNECTED = "android.intent.action.ACTION_POWER_DISCONNECTED"
    }

    override fun onReceive(c: Context, i: Intent) {
        Toast.makeText(c,i.action,Toast.LENGTH_LONG).show()
        if (i.action == "BROADCAST_TEST") {
            val intent = Intent(c,WorkActivity::class.java)
            intent.putExtra("test","test1")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            c.startActivity(intent)
        }
    }
}
