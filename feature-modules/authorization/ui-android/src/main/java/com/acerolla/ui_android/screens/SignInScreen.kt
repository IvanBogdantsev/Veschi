package com.acerolla.ui_android.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
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
import com.acerolla.api.models.SignInModel
import com.acerolla.shared_resources.SharedResources
import com.acerolla.ui_android.AuthViewModel
import com.acerolla.ui_android.screens.components.AuthButton
import com.acerolla.ui_android.screens.components.AuthErrorDialog
import com.acerolla.ui_android.screens.components.AuthTextInput
import com.acerolla.ui_android.screens.components.DividerArea
import com.acerolla.ui_android.screens.components.SignButtonsArea
import com.acerolla.ui_android.utils.isEmailValid
import com.acerolla.ui_android.utils.isPasswordValid
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreen(
    signSuccessed: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignUpBtnClick: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val viewModel = koinViewModel<AuthViewModel>()
    val screenState = viewModel.screenState.collectAsStateWithLifecycle()
    var email by remember { mutableStateOf(emptyString()) }
    var password by remember { mutableStateOf(emptyString()) }
    var isEmailNotValid by remember { mutableStateOf(false) }
    var isPasswordNotValid by remember { mutableStateOf(false) }
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
                .wrapContentHeight(Alignment.CenterVertically)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
                    .padding(horizontal = HORIZONTAL_PADDING),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .padding(horizontal = HORIZONTAL_PADDING),
                    text = stringResource(SharedResources.strings.enter_acc),
                    fontFamily = FontFamily(Font(SharedResources.fonts.Inter.bold.fontResourceId)),
                    fontSize = HEADER_FONT_SIZE
                )
                AuthTextInput(
                    modifier = Modifier
                        .padding(top = 32.dp),
                    value = email,
                    hintTitle = stringResource(id = SharedResources.strings.email),
                    onValueChanged = { email = it },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    ),
                    notValidValue = isEmailNotValid,
                    errorText = stringResource(id = SharedResources.strings.email_not_valid)
                )
                AuthTextInput(
                    modifier = Modifier
                        .padding(top = 8.dp),
                    value = password,
                    hintTitle = stringResource(id = SharedResources.strings.password),
                    onValueChanged = { password = it },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    notValidValue = isPasswordNotValid,
                    errorText = stringResource(id = SharedResources.strings.password_not_valid)
                )
                ForgotPassword(
                    onForgotPasswordClick = onForgotPasswordClick
                )
                AuthButton(
                    title = stringResource(SharedResources.strings.enter),
                    onClick = {
                        if (!email.isEmailValid()) {
                            isEmailNotValid = true
                        } else if (!password.isPasswordValid()) {
                            isEmailNotValid = false
                            isPasswordNotValid = true
                        } else {
                            isPasswordNotValid = false
                            keyboardController?.hide()
                            viewModel.signIn(
                                SignInModel(email, password)
                            )
                        }
                    }
                )
                DividerArea()
                SignButtonsArea(
                    onGoogleBtnClick = {  }, // TODO:
                    onAppleBtnClick = {  }, // TODO:
                )
                NoAccount {
                    onSignUpBtnClick()
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
}

@Composable
private fun ForgotPassword(
    onForgotPasswordClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            modifier = Modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onForgotPasswordClick()
                },
            text = stringResource(id = SharedResources.strings.forgot_pass),
            fontFamily = FontFamily(Font(SharedResources.fonts.Inter.regular.fontResourceId)),
            fontSize = 14.sp,
            color = Color(SharedResources.colors.primary.getColor(LocalContext.current)),
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
private fun NoAccount(
    onRegisterClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = SharedResources.strings.no_account),
            fontFamily = FontFamily(Font(SharedResources.fonts.Inter.regular.fontResourceId)),
            fontSize = 15.sp,
            color = Color.Black
        )
        Text(
            modifier = Modifier
                .padding(start = 10.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onRegisterClick()
                },
            text = stringResource(id = SharedResources.strings.register),
            fontFamily = FontFamily(Font(SharedResources.fonts.Inter.regular.fontResourceId)),
            fontSize = 15.sp,
            color = Color(SharedResources.colors.primary.getColor(LocalContext.current)),
            textDecoration = TextDecoration.Underline
        )
    }
}

@Preview
@Composable
fun SignInScreen_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        SignInScreen(
            signSuccessed = {},
            onForgotPasswordClick = {},
            onSignUpBtnClick = {}
        )
    }
}

@Preview
@Composable
fun SignInScreen_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        SignInScreen(
            signSuccessed = {},
            onForgotPasswordClick = {},
            onSignUpBtnClick = {}
        )
    }
}