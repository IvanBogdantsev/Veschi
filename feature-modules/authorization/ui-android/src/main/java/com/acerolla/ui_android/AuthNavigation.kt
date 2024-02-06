package com.acerolla.ui_android

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.acerolla.ui_android.screens.SignInScreen
import com.acerolla.ui_android.screens.SignUpScreen

val AUTH_NAV_GRAPH_PATTERN = "authNavGraph"
private val LOGIN_PATTERN = "login"
private val REGISTRATION_PATTERN = "registration"

fun NavGraphBuilder.authGraph(
    navController: NavController,
    signSuccessed: () -> Unit
) {
    navigation(
        startDestination = LOGIN_PATTERN,
        route = AUTH_NAV_GRAPH_PATTERN
    ) {
        loginScreen(
            signSuccessed = signSuccessed,
            onLoginRegistrationClick = {
                navController.navigate(REGISTRATION_PATTERN)
            }
        )
        registrationScreen(
            onBackBtnClick = {
                navController.navigate(LOGIN_PATTERN)
            },
            signSuccessed = signSuccessed
        )
    }
}

internal fun NavGraphBuilder.loginScreen(
    signSuccessed: () -> Unit,
    onLoginRegistrationClick: () -> Unit
) {
    composable(LOGIN_PATTERN) {
        SignInScreen()
    }
}

internal fun NavGraphBuilder.registrationScreen(
    signSuccessed: () -> Unit,
    onBackBtnClick: () -> Unit
) {
    composable(REGISTRATION_PATTERN) {
        SignUpScreen()
    }
}