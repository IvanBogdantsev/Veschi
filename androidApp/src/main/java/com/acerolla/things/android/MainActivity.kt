package com.acerolla.things.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import com.acerolla.android_design_system.ThingsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThingsAppTheme {
                Surface {
                    Text(text = "Hello world")
                }
            }
        }
    }
}
