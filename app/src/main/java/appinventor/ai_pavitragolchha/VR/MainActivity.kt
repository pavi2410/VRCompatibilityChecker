package appinventor.ai_pavitragolchha.VR

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

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)

        setSupportActionBar(toolbar)

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
            message.text = getString(R.string.msg_success)
            icon.setImageResource(R.drawable.check)
        } else {
            message.text = getString(R.string.msg_fail)
            icon.setImageResource(R.drawable.cross)
        }

        val results = listOf(
                Item("Accelerometer", accelerometer),
                Item("Compass", compass),
                Item("Gyroscope", gyro),
                Item("Screen Size", screenSize >= 5.0),
                Item("RAM", ram >= 2.GB),
                Item("Android Version", true)
        )

        list_details.apply {
            setHasFixedSize(true)
            adapter = DetailsListAdapter(results)
        }

        // -- AdMob Integration --

        MobileAds.initialize(this, BuildConfig.ADMOB_APP_ID)

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }
}

data class Item(val name: String, val value: Boolean)