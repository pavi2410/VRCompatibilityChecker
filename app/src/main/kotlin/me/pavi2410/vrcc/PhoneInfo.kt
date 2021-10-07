package me.pavi2410.vrcc

import android.app.ActivityManager
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.core.content.getSystemService
import kotlin.math.hypot
import kotlin.math.roundToInt

/**
 * Created by Pavitra on 08-03-2018.
 */
class PhoneInfo(context: Context) {
    private val sensorManager: SensorManager = context.getSystemService()!!
    private val activityManager: ActivityManager = context.getSystemService()!!
    private val windowManager: WindowManager = context.getSystemService()!!

    private fun isSensorAvailable(sensor: Int) = sensorManager.getSensorList(sensor).isNotEmpty()

    private val displayMetrics = DisplayMetrics().apply {
        // TODO: Use new API
        windowManager.defaultDisplay.getRealMetrics(this)
    }

    val hasAccelerometer = isSensorAvailable(Sensor.TYPE_ACCELEROMETER)
    val hasCompass = isSensorAvailable(Sensor.TYPE_GRAVITY)
    val hasGyro = isSensorAvailable(Sensor.TYPE_GYROSCOPE)

    val screenSize: Float = with(displayMetrics) {
        val wi = widthPixels / xdpi // width in inches
        val hi = heightPixels / ydpi // height in inches
        val diagonal = hypot(wi, hi)

        (diagonal * 10).roundToInt() / 10f
    }

    val ram: Long = with(ActivityManager.MemoryInfo()) {
        activityManager.getMemoryInfo(this)

        totalMem
    }
}