package appinventor.ai_pavitragolchha.VR

import android.app.ActivityManager
import android.content.Context
import android.hardware.SensorManager
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Created by Pavitra on 08-03-2018.
 */
class PhoneInfo(val ctx: Context) {

    fun checkSensor(sensor: Int): Boolean {
        val sm = ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        return sm.getDefaultSensor(sensor) != null
    }

    fun getScreenRes(): Pair<Int, Int> {
        val dm = ctx.resources.displayMetrics
        val width = dm.widthPixels
        val height = dm.heightPixels

        return Pair(width, height)
    }

    fun getScreenSize(): Float {
        val (w, h) = getScreenRes()
        val dm = ctx.resources.displayMetrics

        val wi = w / dm.xdpi // width in inches
        val hi = h / dm.ydpi // height in inches

        return sqrt(wi.pow(2) + hi.pow(2))
    }

    fun getRam(): Long {
        val am = ctx.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val mi = ActivityManager.MemoryInfo()
        am.getMemoryInfo(mi)

        return mi.totalMem
    }
}