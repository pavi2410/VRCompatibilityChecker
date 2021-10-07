package me.pavi2410.vrcc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import me.pavi2410.vrcc.ui.VrccTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            VrccTheme {
                ProvideWindowInsets {
                    val mvm: MainViewModel = remember { MainViewModel(this) }
                    MainScreen(mvm)
                }
            }
        }
    }
}