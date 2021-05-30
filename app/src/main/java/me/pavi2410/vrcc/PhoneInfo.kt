package me.pavi2410.vrcc

import android.app.ActivityManager
import android.hardware.SensorManager
import android.util.DisplayMetrics
import android.view.WindowManager
import kotlin.math.hypot
import kotlin.math.roundToInt

/**
 * Created by Pavitra on 08-03-2018.
 */
class PhoneInfo(
        private val sensorManager: SensorManager,
        private val activityManager: ActivityManager,
        private val windowManager: WindowManager
) {
    fun isSensorAvailable(sensor: Int) = sensorManager.getSensorList(sensor).isNotEmpty()

    fun getScreenSize() = with(getDisplayMetrics()) {
        val wi = widthPixels / xdpi // width in inches
        val hi = heightPixels / ydpi // height in inches

        (hypot(wi, hi) * 10.0).roundToInt() / 10.0
    }

    fun getRam() = with(ActivityManager.MemoryInfo()) {
        activityManager.getMemoryInfo(this)
        totalMem
    }

    private fun getDisplayMetrics() = DisplayMetrics().apply {
        windowManager.defaultDisplay.getRealMetrics(this)
    }
}