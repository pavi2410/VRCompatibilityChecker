package tk.pavi2410.vrcc

import android.app.ActivityManager
import android.content.Context
import android.hardware.Sensor.*
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val sensorManager by lazy { getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    private val activityManager by lazy { getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager }

    private val phoneInfo by lazy { PhoneInfo(sensorManager, activityManager, windowManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        val accelerometer = phoneInfo.isSensorAvailable(TYPE_ACCELEROMETER)
        val compass = phoneInfo.isSensorAvailable(TYPE_GRAVITY)
        val gyro = phoneInfo.isSensorAvailable(TYPE_GYROSCOPE)
        val screenSize = phoneInfo.getScreenSize().round()
        val ram = phoneInfo.getRam()

        /*
         * Accelerometer = required
         * Gyro = required
         * Screen Size >= 5"
         * Android >= KitKat
         * RAM >= 2 GB
         */
        if (accelerometer and gyro and (screenSize >= 5) and (ram >= 2.GB)) {
            message_text.setText(R.string.msg_success)
            message_icon.setText(R.string.emoji_success)
        } else {
            message_text.setText(R.string.msg_fail)
            message_icon.setText(R.string.emoji_fail)
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

        list_details.apply {
            setHasFixedSize(true)
            suppressLayout(true)
            adapter = DetailsListAdapter(results)
        }

        // -- AdMob Integration Start --

        MobileAds.initialize(this, BuildConfig.ADMOB_APP_ID)

        val adRequest = AdRequest.Builder().addTestDevice("AF88EDD4F09A6206836CD7B57A8AE037").build()
        adView.loadAd(adRequest)

        // -- AdMob Integration End --
    }

    public override fun onPause() {
        adView.pause()
        super.onPause()
    }

    public override fun onResume() {
        super.onResume()
        adView.resume()
    }

    public override fun onDestroy() {
        adView.destroy()
        super.onDestroy()
    }
}