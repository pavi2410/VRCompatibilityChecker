package me.pavi2410.vrcc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import me.pavi2410.vrcc.ui.VrccTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VrccTheme {
                val mvm: MainViewModel by remember { mutableStateOf(MainViewModel(this)) }
                MainScreen(mvm)
            }
        }
    }
}