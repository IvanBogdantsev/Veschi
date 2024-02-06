package com.acerolla.android_navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@Composable
fun ThingsRootScreen(
    onExitAppClick: () -> Unit
) {
//    val service = koinInject<ThingsNetworkService>()
    val scope = rememberCoroutineScope()
    var uuid by remember { mutableStateOf("") }
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Application")
            Text(text = uuid)
            Button(
                modifier = Modifier
                    .padding(bottom = 20.dp),
                onClick = {
                    scope.launch(Dispatchers.IO) {
                        try {
//                            val secret = service.getSecret()
//                            withContext(Dispatchers.Main) {
//                                uuid = secret.secret
//                            }
                        } catch (e: Exception) {
                            Timber.e(e)
                        }
                    }
                }
            ) {
                Text(text = "Get secret")
            }
            Button(
                modifier = Modifier
                    .padding(bottom = 20.dp),
                onClick = {
                    onExitAppClick()
                }
            ) {
                Text(text = "Log out")
            }
        }
    }
}