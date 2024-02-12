package com.acerolla.ui_android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.acerolla.android_design_system.HORIZONTAL_PADDING
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.android_design_system.components.DialogProgress
import com.acerolla.ui_android.AuthViewModel
import com.acerolla.ui_android.screens.components.AuthErrorDialog
import org.koin.androidx.compose.koinViewModel

enum class AuthScreen {

    SIGN_IN, SIGN_UP
}

@Composable
fun AuthRootScreen(
    onSignSuccessed: () -> Unit,
) {
    val viewModel = koinViewModel<AuthViewModel>()
    val screenState = viewModel.screenState.collectAsStateWithLifecycle()
    var showProgress by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var authScreen by remember { mutableStateOf(AuthScreen.SIGN_IN) }
    showProgress = screenState.value.isLoading
    showError = screenState.value.isErrorOccurred()
    if (screenState.value.successfullySigned) onSignSuccessed()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 10.dp)
                .padding(horizontal = HORIZONTAL_PADDING),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when(authScreen) {
                AuthScreen.SIGN_IN -> {
                    SignInScreen(
                        onAuthBtnClick = viewModel::signIn,
                        onSignUpBtnClick = {
                            authScreen = AuthScreen.SIGN_UP
                        }
                    )
                }
                AuthScreen.SIGN_UP -> {
                    SignUpScreen(
                        onAuthBtnClick = viewModel::signUp,
                        onLoginClick = {
                            authScreen = AuthScreen.SIGN_IN
                        }
                    )
                }
            }
            DialogProgress(
                shouldShow = showProgress,
                dismissOnBackPressed = false,
                dismissOnClickOutside = false
            )
            AuthErrorDialog(
                shouldShow = showError,
                msg = screenState.value.error?.body?.message,
                dismissOnBackPressed = true,
                dismissOnClickOutside = true,
                onDismissClicked = {
                    showError = false
                }
            )
        }
    }
}

@Preview
@Composable
fun AuthRootScreen_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        AuthRootScreen(
            {}
        )
    }
}

@Preview
@Composable
fun AuthRootScreen_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        AuthRootScreen(
            {}
        )
    }
}