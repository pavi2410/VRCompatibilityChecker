package appinventor.ai_pavitragolchha.VR

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun Double.round() = Math.round(this * 10) / 10

val Int.GB get() = this * 1_000_000_000

fun ViewGroup.inflate(@LayoutRes layout: Int) =
        LayoutInflater.from(context).inflate(layout, this, false)