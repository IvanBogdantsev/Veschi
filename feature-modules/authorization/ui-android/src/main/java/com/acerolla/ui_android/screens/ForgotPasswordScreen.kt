package com.acerolla.ui_android.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.acerolla.android_design_system.HORIZONTAL_PADDING
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.shared_resources.SharedResources
import com.acerolla.ui_android.AuthViewModel
import com.acerolla.ui_android.screens.components.AuthButton
import com.acerolla.ui_android.screens.components.AuthTextInput
import org.koin.androidx.compose.koinViewModel

@Composable
fun ForgotPasswordScreen(
    onBackBtnClick: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val viewModel = koinViewModel<AuthViewModel>()
    val screenState = viewModel.screenState.collectAsStateWithLifecycle()
    var email by remember { mutableStateOf("") }
    var showProgress by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    showProgress = screenState.value.isLoading
    showError = screenState.value.isErrorOccurred()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
                    .padding(horizontal = HORIZONTAL_PADDING),
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 6.dp),
                    text = stringResource(id = SharedResources.strings.enter_ur_email_for_password_reset.resourceId),
                    fontFamily = FontFamily(Font(SharedResources.fonts.Inter.bold.fontResourceId)),
                    fontSize = 24.sp
                )
                AuthTextInput(
                    modifier = Modifier
                        .padding(top = 32.dp),
                    value = email,
                    hintTitle = stringResource(id = SharedResources.strings.email.resourceId),
                    onValueChanged = { email = it },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    )
                )
                AuthButton(
                    modifier = Modifier
                        .padding(top = 32.dp),
                    title = stringResource(id = SharedResources.strings.reset_password.resourceId),
                    onClick = {
                        showProgress = true
                        keyboardController?.hide()
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun ForgotPasswordScreen_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        ForgotPasswordScreen(
            {}
        )
    }
}

@Preview
@Composable
private fun ForgotPasswordScreen_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        ForgotPasswordScreen(
            {}
        )
    }
}