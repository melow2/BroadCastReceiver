package com.khs.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BroadCastReceiverEx() : BroadcastReceiver() {

    override fun onReceive(c: Context, i: Intent) {
        if (i.action == "br_test") {
            val intent = Intent(c,WorkActivity::class.java)
            intent.putExtra("test","test1")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            c.startActivity(intent)
        }else if(i.action == "android.intent.action.SCREEN_ON"){
            Toast.makeText(c,"하이",Toast.LENGTH_SHORT).show()
        }
    }
}
