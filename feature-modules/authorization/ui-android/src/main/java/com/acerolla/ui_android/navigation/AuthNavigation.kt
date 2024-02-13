package com.acerolla.ui_android.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.acerolla.api.models.SignUpModel
import com.acerolla.ui_android.screens.CreateAccountScreen
import com.acerolla.ui_android.screens.ForgotPasswordScreen
import com.acerolla.ui_android.screens.SignInScreen
import com.acerolla.ui_android.screens.SignUpScreen

const val AUTH_NAV_GRAPH_PATTERN = "authNavGraph"
private const val SIGN_IN_SCREEN_PATTERN = "SIGN_IN_SCREEN_PATTERN"
private const val SIGN_UP_SCREEN_PATTERN = "SIGN_UP_SCREEN_PATTERN"
private const val CREATE_ACCOUNT_SCREEN_PATTERN = "CREATE_ACCOUNT_SCREEN_PATTERN/{email}/{username}"
private const val CREATE_ACCOUNT_SCREEN_PATTERN_NAV = "CREATE_ACCOUNT_SCREEN_PATTERN"
private const val FORGOT_PASSWORD_SCREEN_PATTERN = "FORGOT_PASSWORD_SCREEN_PATTERN"

fun NavGraphBuilder.authGraph(
    navController: NavController,
    signSuccessed: () -> Unit
) {
    navigation(
        startDestination = SIGN_IN_SCREEN_PATTERN,
        route = AUTH_NAV_GRAPH_PATTERN
    ) {
        signInScreen(
            signSuccessed = signSuccessed,
            onForgotPasswordClick = {
                navController.navigate(FORGOT_PASSWORD_SCREEN_PATTERN)
            },
            onSignUpBtnClick = {
                navController.navigate(SIGN_UP_SCREEN_PATTERN)
            }
        )
        signUpScreen(
            onNextBtnClick = {
                navController.navigateToCreateAccountScreen(it)
            },
            onLoginClick = {
                navController.navigate(SIGN_IN_SCREEN_PATTERN)
            }
        )
        createAccount(
            signSuccessed = signSuccessed
        )
        forgotPassword(
            onBackBtnClick = {
                navController.navigate(SIGN_IN_SCREEN_PATTERN)
            }
        )
    }
}

internal fun NavGraphBuilder.signInScreen(
    signSuccessed: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignUpBtnClick: () -> Unit
) {
    composable(SIGN_IN_SCREEN_PATTERN) {
        SignInScreen(
            signSuccessed = signSuccessed,
            onForgotPasswordClick = onForgotPasswordClick,
            onSignUpBtnClick = onSignUpBtnClick
        )
    }
}

internal fun NavGraphBuilder.signUpScreen(
    onNextBtnClick: (SignUpModel) -> Unit,
    onLoginClick: () -> Unit
) {
    composable(SIGN_UP_SCREEN_PATTERN) {
        SignUpScreen(
            onNextBtnClick = {
                onNextBtnClick(it)
            },
            onLoginClick = onLoginClick
        )
    }
}

internal fun NavGraphBuilder.createAccount(
    signSuccessed: () -> Unit
) {
    composable(
        route = CREATE_ACCOUNT_SCREEN_PATTERN,
        arguments = listOf(
            navArgument("email") { type = NavType.StringType },
            navArgument("username") { type = NavType.StringType },
        )
    ) {
        CreateAccountScreen(
            email = it.arguments?.getString("email"),
            username = it.arguments?.getString("username"),
            signSuccessed = signSuccessed
        )
    }
}

internal fun NavGraphBuilder.forgotPassword(
    onBackBtnClick: () -> Unit
) {
    composable(FORGOT_PASSWORD_SCREEN_PATTERN) {
        ForgotPasswordScreen(
            onBackBtnClick = onBackBtnClick
        )
    }
}

internal fun NavController.navigateToCreateAccountScreen(model: SignUpModel) {
    this.navigate("$CREATE_ACCOUNT_SCREEN_PATTERN_NAV/${model.email}/${model.username}")
}