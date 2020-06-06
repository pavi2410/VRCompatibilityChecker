package tk.pavi2410.vrcc

import android.app.ActivityManager
import android.hardware.Sensor.*
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.getSystemService
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import tk.pavi2410.vrcc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val sensorManager by lazy { getSystemService<SensorManager>()!! }
    private val activityManager by lazy { getSystemService<ActivityManager>()!! }

    private val phoneInfo by lazy { PhoneInfo(sensorManager, activityManager, windowManager) }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        initUI()

        initAdView()
    }

    private fun initUI() {
        val accelerometer = phoneInfo.isSensorAvailable(TYPE_ACCELEROMETER)
        val compass = phoneInfo.isSensorAvailable(TYPE_GRAVITY)
        val gyro = phoneInfo.isSensorAvailable(TYPE_GYROSCOPE)
        val screenSize = phoneInfo.getScreenSize()
        val ram = phoneInfo.getRam()

        /*
         * Accelerometer = required
         * Gyro = required
         * Screen Size >= 5"
         * Android >= Lollipop (API 21) == Min SDK version
         * RAM >= 2 GB
         */
        if (accelerometer and gyro and (screenSize >= 5.0) and (ram >= 2.GB)) {
            binding.messageText.setText(R.string.msg_success)
            binding.messageIcon.setText(R.string.emoji_success)
        } else {
            binding.messageText.setText(R.string.msg_fail)
            binding.messageIcon.setText(R.string.emoji_fail)
        }

        val results = listOf(
                Item(
                        icon = R.drawable.accelerometer,
                        name = R.string.accelerometer,
                        result = accelerometer
                ),
                Item(
                        icon = R.drawable.compass,
                        name = R.string.compass,
                        result = compass
                ),
                Item(
                        icon = R.drawable.gyroscope,
                        name = R.string.gyroscope,
                        result = gyro
                ),
                Item(
                        icon = R.drawable.screen_size,
                        name = R.string.screen_size,
                        result = screenSize >= 5.0
                ),
                Item(
                        icon = R.drawable.ram,
                        name = R.string.ram,
                        result = ram >= 2.GB
                ),
                Item(
                        icon = R.drawable.android_version,
                        name = R.string.android_version,
                        result = true
                )
        )

        binding.listDetails.apply {
            setHasFixedSize(true)
            suppressLayout(true)

            adapter = DetailsListAdapter(results)
        }
    }

    private fun initAdView() {
        MobileAds.initialize(this)

        val testDeviceRC = RequestConfiguration.Builder().setTestDeviceIds(listOf("AF88EDD4F09A6206836CD7B57A8AE037")).build()
        MobileAds.setRequestConfiguration(testDeviceRC)

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    public override fun onPause() {
        binding.adView.pause()
        super.onPause()
    }

    public override fun onResume() {
        super.onResume()
        binding.adView.resume()
    }

    public override fun onDestroy() {
        binding.adView.destroy()
        super.onDestroy()
    }
}

val Int.GB get() = this * 1_000_000_000