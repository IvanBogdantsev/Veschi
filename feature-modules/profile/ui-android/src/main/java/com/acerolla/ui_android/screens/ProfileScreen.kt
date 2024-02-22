package com.acerolla.ui_android.screens

import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acerolla.android_design_system.HORIZONTAL_PADDING
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.api.AuthStatusChecker
import com.acerolla.api.AuthorizationNetworkService
import com.acerolla.api.models.AuthStatus
import com.acerolla.common.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.compose.koinInject
import timber.log.Timber

@Composable
fun ProfileScreen(
    onExitAppClick: () -> Unit
) {
    val service = koinInject<AuthorizationNetworkService>()
    val authChecker = koinInject<AuthStatusChecker>()
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
            Text(
                modifier = Modifier
                    .padding(horizontal = HORIZONTAL_PADDING),
                text = uuid
            )
            Button(
                modifier = Modifier
                    .padding(bottom = 20.dp),
                onClick = {
                    scope.launch(Dispatchers.IO) {
                        try {
                            uuid = "LOADING"
                            val secret = service.getSecret()
                            withContext(Dispatchers.Main) {
                                when(secret) {
                                    is ApiResponse.Success -> {
                                        uuid = secret.body.secret
                                    }
                                    else -> {
                                        Log.d("TAG1", "ThingsRootScreen: $secret")
                                    }
                                }
                            }
                        } catch (e: Exception) {
                            uuid = "ERROR"
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
                    scope.launch {
                        authChecker.setAuthStatus(AuthStatus(false))
                    }
                    onExitAppClick()
                }
            ) {
                Text(text = "Log out")
            }
        }
    }
}

@Preview
@Composable
fun AddThingScreen_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        ProfileScreen({})
    }
}

@Preview
@Composable
fun AddThingScreen_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        ProfileScreen({})
    }
}