package me.pavi2410.vrcc

import android.hardware.Sensor.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import me.pavi2410.vrcc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        initUI()
    }

    private fun initUI() {
        val phoneInfo = PhoneInfo(this, windowManager)

        val hasAccelerometer = phoneInfo.isSensorAvailable(TYPE_ACCELEROMETER)
        val hasCompass = phoneInfo.isSensorAvailable(TYPE_GRAVITY)
        val hasGyro = phoneInfo.isSensorAvailable(TYPE_GYROSCOPE)
        val screenSize = phoneInfo.getScreenSize()
        val ram = phoneInfo.getRam()

        /* COMPATIBILITY CRITERIA
         * ------------------------
         * Accelerometer = required
         * Gyro = required
         * Screen Size >= 5"
         * Android >= Lollipop (API 21) == Min SDK version
         * RAM >= 2 GB
         */
        if (hasAccelerometer and hasGyro and (screenSize >= 5.0) and (ram >= 2.GB)) {
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
                        result = hasAccelerometer
                ),
                Item(
                        icon = R.drawable.compass,
                        name = R.string.compass,
                        result = hasCompass
                ),
                Item(
                        icon = R.drawable.gyroscope,
                        name = R.string.gyroscope,
                        result = hasGyro
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
}

private inline val Int.GB get() = this * 1_000_000_000