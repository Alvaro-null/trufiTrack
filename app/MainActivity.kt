// app/MainActivity.kt
package com.trufitrack.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.trufitrack.presentation.navigation.AppNavigation
import com.trufitrack.presentation.theme.TrufiTrackTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrufiTrackTheme {
                AppNavigation()
            }
        }
    }
}