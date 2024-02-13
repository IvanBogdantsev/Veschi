package com.acerolla.ui_android.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.acerolla.android_design_system.HEADER_FONT_SIZE
import com.acerolla.android_design_system.HORIZONTAL_PADDING
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.android_design_system.components.DialogProgress
import com.acerolla.android_design_system.emptyString
import com.acerolla.android_design_system.stringResource
import com.acerolla.api.models.SignUpModel
import com.acerolla.shared_resources.SharedResources
import com.acerolla.ui_android.AuthViewModel
import com.acerolla.ui_android.screens.components.AuthButton
import com.acerolla.ui_android.screens.components.AuthErrorDialog
import com.acerolla.ui_android.screens.components.AuthTextInput
import com.acerolla.ui_android.screens.components.RoundCheckbox
import com.acerolla.ui_android.utils.isPasswordValid
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateAccountScreen(
    email: String? = emptyString(),
    username: String? = emptyString(),
    signSuccessed: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val viewModel = koinViewModel<AuthViewModel>()
    val screenState = viewModel.screenState.collectAsStateWithLifecycle()
    var password by remember { mutableStateOf(emptyString()) }
    var repeatPassword by remember { mutableStateOf(emptyString()) }
    var isPoliticsAgreed by remember { mutableStateOf(false) }
    var isPasswordNotValid by remember { mutableStateOf(false) }
    var isRepeatedPasswordNotValid by remember { mutableStateOf(false) }
    var showProgress by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    showProgress = screenState.value.isLoading
    showError = screenState.value.isErrorOccurred()
    if (screenState.value.successfullySigned) signSuccessed()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .padding(top = 32.dp)
                .wrapContentHeight(Alignment.CenterVertically)
                .padding(horizontal = HORIZONTAL_PADDING)
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .wrapContentHeight(Alignment.CenterVertically)
                    .padding(horizontal = HORIZONTAL_PADDING)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = 6.dp),
                        text = stringResource(SharedResources.strings.create_password),
                        fontFamily = FontFamily(Font(SharedResources.fonts.Inter.bold.fontResourceId)),
                        fontSize = HEADER_FONT_SIZE
                    )
                    AuthTextInput(
                        modifier = Modifier
                            .padding(top = 32.dp),
                        value = password,
                        hintTitle = stringResource(id = SharedResources.strings.password),
                        onValueChanged = { password = it },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Text
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        notValidValue = isPasswordNotValid,
                        errorText = stringResource(id = SharedResources.strings.password_not_valid)
                    )
                    AuthTextInput(
                        modifier = Modifier
                            .padding(top = 5.dp),
                        value = repeatPassword,
                        hintTitle = stringResource(id = SharedResources.strings.repeat_pas),
                        onValueChanged = { repeatPassword = it },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Text
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        errorText = stringResource(id = SharedResources.strings.password_not_valid)
                    )
                    ConfPoliticsArea(
                        checked = isPoliticsAgreed,
                        onClick = { isPoliticsAgreed = !isPoliticsAgreed }
                    )
                    AuthButton(
                        title = stringResource(id = SharedResources.strings.register),
                        onClick = {
                            if (!password.isPasswordValid()) {
                                isPasswordNotValid = true
                            } else if (password != repeatPassword) {
                                isPasswordNotValid = false
                                isRepeatedPasswordNotValid = true
                            } else {
                                isRepeatedPasswordNotValid = false
                                viewModel.signUp(
                                    SignUpModel(
                                        email ?: emptyString(),
                                        username ?: emptyString(),
                                        password
                                    )
                                )
                                keyboardController?.hide()
                            }
                        }
                    )
                }
            }
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

@Composable
private fun ConfPoliticsArea(
    checked: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RoundCheckbox(
            checked = checked,
            onClick = onClick
        )
        Text(
            modifier = Modifier
                .padding(start = 5.dp),
            text = stringResource(id = SharedResources.strings.apply_conf),
            fontFamily = FontFamily(Font(SharedResources.fonts.Inter.regular.fontResourceId)),
            fontSize = 12.sp,
            color = Color.Black,
            lineHeight = 15.sp
        )
    }
}

@Preview
@Composable
private fun CreateAccountScreen_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        CreateAccountScreen(
            email = "",
            username = "",
            signSuccessed = {}
        )
    }
}

@Preview
@Composable
private fun CreateAccountScreen_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        CreateAccountScreen(
            email = "",
            username = "",
            signSuccessed = {}
        )
    }
}