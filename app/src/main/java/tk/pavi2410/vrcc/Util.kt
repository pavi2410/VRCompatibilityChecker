package tk.pavi2410.vrcc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import kotlin.math.roundToLong

fun Double.round() = (this * 10).roundToLong() / 10

val Int.GB get() = this * 1_000_000_000

fun ViewGroup.inflate(@LayoutRes layout: Int) =
        LayoutInflater.from(context).inflate(layout, this, false)!!