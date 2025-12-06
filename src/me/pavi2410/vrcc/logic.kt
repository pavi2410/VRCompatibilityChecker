package me.pavi2410.vrcc

import android.content.Context
import android.os.Build
import me.pavi2410.vrcc.models.DetailModel
import me.pavi2410.vrcc.ui.Colors
import me.pavi2410.vrcc.ui.Icons
import kotlin.math.roundToInt

typealias CheckResults = Pair<Boolean, List<DetailModel>>

fun computeCompatibilityResults(context: Context): CheckResults {
    val phoneInfo = PhoneInfo(context)

    /** COMPATIBILITY CRITERIA
     * ------------------------
     * - Accelerometer = required
     * - Gyro = required
     * - Screen Size >= 5"
     * - Android >= Lollipop (API 21) == Min SDK version
     * - RAM >= 2 GB
     */
    val isCompatible = with(phoneInfo) {
        hasAccelerometer and hasGyro and (screenSize >= 5.0) and (ram >= 2.GB)
    }

    val details = with(phoneInfo) {
        listOf(
            DetailModel(
                icon = Icons.Accelerometer,
                iconColor = Colors.Indigo500,
                name = "Accelerometer",
                result = hasAccelerometer
            ),
            DetailModel(
                icon = Icons.Compass,
                iconColor = Colors.Red500,
                name = "Compass",
                result = hasCompass
            ),
            DetailModel(
                icon = Icons.Gyroscope,
                iconColor = Colors.Yellow500,
                name = "Gyroscope",
                result = hasGyro
            ),
            DetailModel(
                icon = Icons.Screen,
                iconColor = Colors.Blue500,
                name = "Screen Size",
                result = screenSize >= 5.0,
                detail = "$screenSize”"
            ),
            DetailModel(
                icon = Icons.Ram,
                iconColor = Colors.Purple500,
                name = "RAM",
                result = ram >= 2.GB,
                detail = "${(ram * 10 / 1.GB).roundToInt() / 10f} GB"
            ),
            DetailModel(
                icon = Icons.Android,
                iconColor = Colors.Green500,
                name = "Android ${Build.VERSION.RELEASE}",
                result = true,
                detail = "API ${Build.VERSION.SDK_INT}"
            )
        )
    }

    return CheckResults(isCompatible, details)
}

private inline val Int.GB get() = this * 1e9