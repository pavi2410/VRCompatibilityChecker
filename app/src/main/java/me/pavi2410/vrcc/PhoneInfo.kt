package me.pavi2410.vrcc

import android.app.ActivityManager
import android.content.Context
import android.hardware.SensorManager
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.core.content.getSystemService
import kotlin.math.hypot
import kotlin.math.roundToInt

/**
 * Created by Pavitra on 08-03-2018.
 */
class PhoneInfo(
        context: Context,
        private val windowManager: WindowManager,
) {
    private val sensorManager = context.getSystemService<SensorManager>()!!
    private val activityManager = context.getSystemService<ActivityManager>()!!

    fun isSensorAvailable(sensor: Int) = sensorManager.getSensorList(sensor).isNotEmpty()

    fun getScreenSize(): Float = with(getDisplayMetrics()) {
        val wi = widthPixels / xdpi // width in inches
        val hi = heightPixels / ydpi // height in inches

        (hypot(wi, hi) * 10).roundToInt() / 10f
    }

    fun getRam(): Long = with(ActivityManager.MemoryInfo()) {
        activityManager.getMemoryInfo(this)
        totalMem
    }

    private fun getDisplayMetrics() = DisplayMetrics().apply {
        // TODO: Use new API
        windowManager.defaultDisplay.getRealMetrics(this)
    }
}