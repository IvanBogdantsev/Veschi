package com.acerolla.things.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.android_navigation.RootNavHost

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThingsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    RootNavHost()
                }
            }
        }
    }
}
