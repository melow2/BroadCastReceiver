package com.khs.broadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.khs.broadcastreceiver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var broadCastReceiverEx: BroadCastReceiverEx

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        broadCastReceiverEx = BroadCastReceiverEx(mBinding.tvBroadcast)
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter();
        filter.addAction(Intent.ACTION_BOOT_COMPLETED)              // 폰 부팅 완료.
        filter.addAction(Intent.ACTION_DATE_CHANGED)                // 날짜가 바뀌었을 때
        filter.addAction(Intent.ACTION_TIME_CHANGED)                // 시간이 바뀌었을 때.
        filter.addAction(Intent.ACTION_TIMEZONE_CHANGED)            // 해외로 갔을 때.
        filter.addAction(Intent.ACTION_LOCALE_CHANGED)              // 해외로 갔을 때.
        filter.addAction(Intent.ACTION_SCREEN_OFF)                  // 스크린이 꺼졌을때.
        filter.addAction(Intent.ACTION_SCREEN_ON)                   // 스크린이 켜졌을때.
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)       // 비행기모드.
        filter.addAction(Intent.ACTION_BATTERY_CHANGED)             // 배터리 상태가 바뀌었을때.(발열)
        filter.addAction(Intent.ACTION_BATTERY_LOW)                 // 배터리 없음.
        filter.addAction(Intent.ACTION_BATTERY_OKAY)                // 배터리 풀.
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        registerReceiver(broadCastReceiverEx,filter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(broadCastReceiverEx)
    }
}