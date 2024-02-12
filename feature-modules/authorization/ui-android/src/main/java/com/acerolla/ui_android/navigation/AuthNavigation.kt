package com.acerolla.ui_android.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.acerolla.ui_android.screens.AuthRootScreen
import com.acerolla.ui_android.screens.SignInScreen
import com.acerolla.ui_android.screens.SignUpScreen

val AUTH_NAV_GRAPH_PATTERN = "authNavGraph"
private val AUTH_ROOT_SCREEN_PATTERN = "AUTH_ROOT_SCREEN_PATTERN"
private val SIGN_IN_SCREEN_PATTERN = "SIGN_IN_SCREEN_PATTERN"
private val SIGN_UP_SCREEN_PATTERN = "SIGN_UP_SCREEN_PATTERN"

fun NavGraphBuilder.authGraph(
    navController: NavController,
    signSuccessed: () -> Unit
) {
    navigation(
        startDestination = AUTH_ROOT_SCREEN_PATTERN,
        route = AUTH_NAV_GRAPH_PATTERN
    ) {
        authRootScreen(
            signSuccessed = signSuccessed
        )
        signInScreen(
            signSuccessed = signSuccessed
        )
        signUpScreen(
            signSuccessed = signSuccessed
        )
    }
}

internal fun NavGraphBuilder.authRootScreen(
    signSuccessed: () -> Unit
) {
    composable(AUTH_ROOT_SCREEN_PATTERN) {
        AuthRootScreen(
            onSignSuccessed = signSuccessed
        )
    }
}

internal fun NavGraphBuilder.signInScreen(
    signSuccessed: () -> Unit
) {
    composable(SIGN_IN_SCREEN_PATTERN) {
        SignInScreen(
            onAuthBtnClick = {},
            onSignUpBtnClick = {}
        )
    }
}

internal fun NavGraphBuilder.signUpScreen(
    signSuccessed: () -> Unit
) {
    composable(SIGN_UP_SCREEN_PATTERN) {
        SignUpScreen(
            onAuthBtnClick = {},
            onLoginClick = {}
        )
    }
}