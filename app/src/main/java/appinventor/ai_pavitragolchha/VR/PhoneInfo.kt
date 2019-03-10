package appinventor.ai_pavitragolchha.VR

import android.app.ActivityManager
import android.content.Context
import android.hardware.SensorManager
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * Created by Pavitra on 08-03-2018.
 */
class PhoneInfo(private val ctx: Context) {

    private val sensorManager = ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val activityManager = ctx.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    private val windowManager = ctx.getSystemService(Context.WINDOW_SERVICE) as WindowManager

    fun checkSensor(sensor: Int) = sensorManager.getSensorList(sensor).isNotEmpty()

    fun getScreenSize() = with(getDisplayMetrics()) {
        val wi = widthPixels / xdpi // width in inches
        val hi = heightPixels / ydpi // height in inches
        Math.hypot(wi.toDouble(), hi.toDouble())
    }

    fun getRam() = with(ActivityManager.MemoryInfo()) {
        activityManager.getMemoryInfo(this)
        totalMem
    }

    private fun getDisplayMetrics() = DisplayMetrics().apply {
        windowManager.defaultDisplay.getRealMetrics(this)
    }
}