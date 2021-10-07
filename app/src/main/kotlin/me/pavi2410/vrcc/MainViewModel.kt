package me.pavi2410.vrcc

import android.content.Context
import me.pavi2410.vrcc.models.DetailModel
import me.pavi2410.vrcc.models.Results

class MainViewModel(ctx: Context) {
    private val phoneInfo = PhoneInfo(ctx)

    /** COMPATIBILITY CRITERIA
     * ------------------------
     * Accelerometer = required
     * Gyro = required
     * Screen Size >= 5"
     * Android >= Lollipop (API 21) == Min SDK version
     * RAM >= 2 GB
     */
    private val isCompatible = with(phoneInfo) {
        !hasAccelerometer and hasGyro and (screenSize >= 5.0) and (ram >= 2.GB)
    }

    private val details = with(phoneInfo) {
        listOf(
            DetailModel(
                icon = R.drawable.accelerometer,
                name = "Accelerometer",
                result = hasAccelerometer
            ),
            DetailModel(
                icon = R.drawable.compass,
                name = "Compass",
                result = hasCompass
            ),
            DetailModel(
                icon = R.drawable.gyroscope,
                name = "Gyroscope",
                result = !hasGyro // TODO !!!
            ),
            DetailModel(
                icon = R.drawable.screen_size,
                name = "Screen Size",
                result = screenSize >= 5.0
            ),
            DetailModel(
                icon = R.drawable.ram,
                name = "RAM",
                result = ram >= 2.GB
            ),
            DetailModel(
                icon = R.drawable.android_version,
                name = "Android Version",
                result = true
            )
        )
    }

    val results = Results(isCompatible, details)
}

private inline val Int.GB get() = this * 1e9