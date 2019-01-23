package appinventor.ai_pavitragolchha.VR

import android.app.ActivityManager
import android.content.Context
import android.hardware.SensorManager
import android.util.DisplayMetrics
import android.view.WindowManager
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Created by Pavitra on 08-03-2018.
 */
class PhoneInfo(private val ctx: Context) {

    fun checkSensor(sensor: Int): Boolean {
        val sm = ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        return sm.getSensorList(sensor).size > 0
    }

    fun getScreenSize(): Float {
        val dm = getDisplayMetrics()

        val wi = dm.widthPixels / dm.xdpi // width in inches
        val hi = dm.heightPixels / dm.ydpi // height in inches

        return sqrt(wi.pow(2) + hi.pow(2))
    }

    fun getRam(): Long {
        val am = ctx.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val mi = ActivityManager.MemoryInfo()

        am.getMemoryInfo(mi)

        return mi.totalMem
    }

    private fun getDisplayMetrics(): DisplayMetrics {
        val wm = ctx.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()

        wm.defaultDisplay.getRealMetrics(dm)

        return dm
    }
}