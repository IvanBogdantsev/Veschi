package com.acerolla.ui_android.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.acerolla.android_design_system.HORIZONTAL_PADDING
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.ui_android.AuthViewModel
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

@Composable
fun AuthRootScreen(
    onSignSuccessed: () -> Unit
) {
    val viewModel = koinViewModel<AuthViewModel>()
    val screenState = viewModel.screenState.collectAsStateWithLifecycle()
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
            when {
                screenState.value.successfullySigned -> {
                    onSignSuccessed()
                }
                screenState.value.forgotPassword -> {}
                screenState.value.signIn -> {
                    SignInScreen(
                        onAuthBtnClick = viewModel::signIn,
                        onSignUpBtnClick = viewModel::moveToSignUpBtnClick
                    )
                }
                screenState.value.signUp -> {
                    SignUpScreen(
                        onAuthBtnClick = viewModel::signUp,
                        onLoginClick = viewModel::moveToSignInBtnClick
                    )
                }
                screenState.value.isErrorOccurred() -> {
                    Toast.makeText(LocalContext.current, "Error ${screenState.value.error}", Toast.LENGTH_SHORT).show()
                    Timber.wtf("EXCEPTION -> ${screenState.value.error?.body}")
                    Timber.e(Exception(screenState.value.error?.msg))
                }
                screenState.value.isLoading -> {
                    Toast.makeText(LocalContext.current, "Loading", Toast.LENGTH_SHORT).show()
                }
            }
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