package me.pavi2410.vrcc

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import me.pavi2410.vrcc.models.DetailModel
import me.pavi2410.vrcc.models.Results
import me.pavi2410.vrcc.ui.Colors
import me.pavi2410.vrcc.ui.Icons
import kotlin.math.roundToInt

@Composable
fun computeCompatibilityResults(): Results {

    val context = LocalContext.current
    val phoneInfo = remember { PhoneInfo(context) }

    /** COMPATIBILITY CRITERIA
     * ------------------------
     * Accelerometer = required
     * Gyro = required
     * Screen Size >= 5"
     * Android >= Lollipop (API 21) == Min SDK version
     * RAM >= 2 GB
     */
    val isCompatible = remember(phoneInfo) {
        with(phoneInfo) {
            hasAccelerometer and hasGyro and (screenSize >= 5.0) and (ram >= 2.GB)
        }
    }

    val details = remember(phoneInfo) {
        with(phoneInfo) {
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
                    detail = "${(ram * 10 / 1e9).roundToInt() / 10f} GB"
                ),
                DetailModel(
                    icon = Icons.Android,
                    iconColor = Colors.Green500,
                    name = "Android Version",
                    result = true,
                    detail = "${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})"
                )
            )
        }
    }

    return remember(phoneInfo) { Results(isCompatible, details) }
}

private inline val Int.GB get() = this * 1e9