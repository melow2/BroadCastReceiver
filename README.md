# BroadCastReceiver
안드로이드 브로드캐스트 리시버에 대한 설명.

## Concept
* 시스템은 여러가지 상황에서 다양한 메세지 방송(BroadCast)을 한다.
* 예를 들어 배터리 부족, 전원 공급장치 변화, 네트워크 환경 변화 등등..
* 이러한 시스템의 환경 변화에 따른 방송을 응용프로그램에서는 항상 참고하여 적정히 대응해야 한다.
* 응용 프로그램에서 시스템의 방송에 귀를 기울이는 것을 **BR(BroadCast Receiver)** 라고한다.

## Implementation

#### 첫 번째 (manifest를 등록하는 방법 - 주로 사용하는 방법)
* BroadCastReceiver를 상속받은 클래스를 제작.
```
class BroadCastReceiverEx : BroadcastReceiver() {

    companion object {
        const val TAG = "BroadCastReceiverEx"
        const val STRING_POWER_CONNECTED = "android.intent.action.ACTION_POWER_CONNECTED"
        const val STRING_POWER_DISCONNECTED = "android.intent.action.ACTION_POWER_DISCONNECTED"
    }

    override fun onReceive(c: Context, i: Intent) {
        // 필요한 브로드캐스트만 처리.
        val intent = Intent(c, MainActivity::class.java)
        if (i.action == STRING_POWER_CONNECTED) {
            intent.putExtra("powerStatus", "power is connected")      // 단말기에 파워가 연결되어 있을 경우.
        } else if (i.action == STRING_POWER_DISCONNECTED) {
            intent.putExtra("powerStatus", "power is disconnected")   // 단말기에 파워가 연결되어 있지 않은 경우.
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        c.startActivity(intent)
    }
}
```
* manifest.xml에 receiver 등록.
```
<receiver android:name=".BroadCastReceiverEx">
    <intent-filter>
        <action android:name="android.intent.action.ACTION_POWER_CONNECTED" />
        <action android:name="android.intent.action.ACTION_POWER_DISCONNECTED" />
    </intent-filter>
</receiver>
```
#
#### 두 번째(소스코드로 작성하는 방법)
* BroadCastReceiver를 상속받은 클래스를 제작.
```
class BroadCastReceiverEx(
    val tv:TextView
) : BroadcastReceiver() {

    companion object {
        const val TAG = "BroadCastReceiverEx"
        const val STRING_POWER_CONNECTED = "android.intent.action.ACTION_POWER_CONNECTED"
        const val STRING_POWER_DISCONNECTED = "android.intent.action.ACTION_POWER_DISCONNECTED"
    }

    override fun onReceive(c: Context, i: Intent) {
        if (i.action == STRING_POWER_CONNECTED) {
            tv.setText("power id CONNECTED")
        } else if (i.action == STRING_POWER_DISCONNECTED) {
            tv.setText("power id DISCONNECTED")
        }
    }
}
```

* 소스 코드 작성(manifest에 작성X)
```
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
```
#
#### 세 번째(sendBroadCast())
시스템에서 송신된 내용을 개발자가 수신할 수도 있지만, 개발자가 직접 송신 할 수도 있다.




