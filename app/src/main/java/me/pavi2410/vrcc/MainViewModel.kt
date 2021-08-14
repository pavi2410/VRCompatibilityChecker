package me.pavi2410.vrcc

import android.content.Context
import android.hardware.Sensor
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import me.pavi2410.vrcc.models.DetailModel
import me.pavi2410.vrcc.models.ResultModel

class MainViewModel(ctx: Context) {
    private val phoneInfo = PhoneInfo(ctx)

    private val hasAccelerometer = phoneInfo.isSensorAvailable(Sensor.TYPE_ACCELEROMETER)
    private val hasCompass = phoneInfo.isSensorAvailable(Sensor.TYPE_GRAVITY)
    private val hasGyro = phoneInfo.isSensorAvailable(Sensor.TYPE_GYROSCOPE)
    private val screenSize = phoneInfo.getScreenSize()
    private val ram = phoneInfo.getRam()

    private val isCompatible = flow {
        /* COMPATIBILITY CRITERIA
         * ------------------------
         * Accelerometer = required
         * Gyro = required
         * Screen Size >= 5"
         * Android >= Lollipop (API 21) == Min SDK version
         * RAM >= 2 GB
         */
        val result = if (hasAccelerometer and hasGyro and (screenSize >= 5.0) and (ram >= 2.GB)) {
            Pair(R.string.msg_success, R.string.emoji_success)
        } else {
            Pair(R.string.msg_fail, R.string.emoji_fail)
        }

        emit(result)
    }

    private val details = flow {
        val results = listOf(
            DetailModel(
                icon = R.drawable.accelerometer,
                name = R.string.accelerometer,
                result = hasAccelerometer
            ),
            DetailModel(
                icon = R.drawable.compass,
                name = R.string.compass,
                result = hasCompass
            ),
            DetailModel(
                icon = R.drawable.gyroscope,
                name = R.string.gyroscope,
                result = hasGyro
            ),
            DetailModel(
                icon = R.drawable.screen_size,
                name = R.string.screen_size,
                result = screenSize >= 5.0
            ),
            DetailModel(
                icon = R.drawable.ram,
                name = R.string.ram,
                result = ram >= 2.GB
            ),
            DetailModel(
                icon = R.drawable.android_version,
                name = R.string.android_version,
                result = true
            )
        )

        emit(results)
    }

    val result = flow {
        emit(null)

        isCompatible.collect { isc ->
            details.collect { d ->
                emit(ResultModel(isCompatible = isc, detailModel = d))
            }
        }
    }
}

private inline val Int.GB get() = this * 1_000_000_000