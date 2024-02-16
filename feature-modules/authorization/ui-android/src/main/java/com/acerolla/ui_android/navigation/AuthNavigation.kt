package com.acerolla.ui_android.navigation

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.acerolla.android_design_system.HORIZONTAL_PADDING
import com.acerolla.api.AuthorizationNetworkService
import com.acerolla.api.models.SignUpModel
import com.acerolla.common.ApiResponse
import com.acerolla.ui_android.screens.CreateAccountScreen
import com.acerolla.ui_android.screens.ForgotPasswordScreen
import com.acerolla.ui_android.screens.SignInScreen
import com.acerolla.ui_android.screens.SignUpScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.compose.koinInject
import timber.log.Timber

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


// for tests
val ThingsNavGraphPattern = "thingsNavGraph"
private val rootScreenPattern = "rootScreen"

fun NavController.navigateToAuthGraph(navOptions: NavOptions? = null) {
    this.navigate(AUTH_NAV_GRAPH_PATTERN, navOptions)
}

fun NavController.navigateToThingsGraph(navOptions: NavOptions? = null) {
    this.navigate(ThingsNavGraphPattern, navOptions)
}

fun NavGraphBuilder.thingsGraph(
    onExitClick: () -> Unit
) {
    navigation(
        startDestination = rootScreenPattern,
        route = ThingsNavGraphPattern
    ) {
        rootScreen(onExitClick)
    }
}

internal fun NavGraphBuilder.rootScreen(
    onExitClick: () -> Unit
) {
    composable(rootScreenPattern) {
        ThingsRootScreen {
            onExitClick()
        }
    }
}

@Composable
fun ThingsRootScreen(
    onExitAppClick: () -> Unit
) {
    val service = koinInject<AuthorizationNetworkService>()
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
                    onExitAppClick()
                }
            ) {
                Text(text = "Log out")
            }
        }
    }
}