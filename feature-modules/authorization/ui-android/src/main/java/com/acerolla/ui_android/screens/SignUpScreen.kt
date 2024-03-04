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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.acerolla.android_design_system.HEADER_FONT_SIZE
import com.acerolla.android_design_system.HORIZONTAL_PADDING
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.android_design_system.emptyString
import com.acerolla.android_design_system.stringResource
import com.acerolla.add_thing_api.models.SignUpModel
import com.acerolla.shared_resources.SharedResources
import com.acerolla.ui_android.screens.components.AuthButton
import com.acerolla.ui_android.screens.components.AuthTextInput
import com.acerolla.ui_android.utils.isEmailValid
import com.acerolla.ui_android.utils.isUsernameValid

@Composable
fun SignUpScreen(
    onNextBtnClick: (SignUpModel) -> Unit,
    onLoginClick: () -> Unit
) {
    var username by remember { mutableStateOf(emptyString()) }
    var email by remember { mutableStateOf(emptyString()) }
    var isEmailNotValid by remember { mutableStateOf(false) }
    var isUsernameNotValid by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
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
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 6.dp),
                    text = stringResource(SharedResources.strings.register),
                    fontFamily = FontFamily(Font(SharedResources.fonts.Inter.bold.fontResourceId)),
                    fontSize = HEADER_FONT_SIZE
                )
                AuthTextInput(
                    modifier = Modifier
                        .padding(top = 32.dp),
                    value = username,
                    hintTitle = stringResource(id = SharedResources.strings.username),
                    onValueChanged = { username = it },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    ),
                    notValidValue = isUsernameNotValid,
                    errorText = stringResource(id = SharedResources.strings.username_not_valid)
                )
                AuthTextInput(
                    modifier = Modifier
                        .padding(top = 8.dp),
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
                AuthButton(
                    title = stringResource(id = SharedResources.strings.next),
                    onClick = {
                        if (!username.isUsernameValid()) {
                            isUsernameNotValid = true
                        } else if (!email.isEmailValid()) {
                            isUsernameNotValid = false
                            isEmailNotValid = true
                        } else {
                            isEmailNotValid = false
                            onNextBtnClick(
                                SignUpModel(
                                    email,
                                    username,
                                    emptyString()
                                )
                            )
                            keyboardController?.hide()
                        }
                    }
                )
                AlreadyHaveAccount(
                    onLoginClick = onLoginClick
                )
            }
        }
    }
}

@Composable
private fun AlreadyHaveAccount(
    onLoginClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = SharedResources.strings.already_have_account),
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
                    onLoginClick()
                },
            text = stringResource(id = SharedResources.strings.enter),
            fontFamily = FontFamily(Font(SharedResources.fonts.Inter.regular.fontResourceId)),
            fontSize = 15.sp,
            color = Color(SharedResources.colors.primary.getColor(LocalContext.current)),
            textDecoration = TextDecoration.Underline
        )
    }
}

@Preview
@Composable
fun SignUpScreen_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        SignUpScreen(
            {}, {}
        )
    }
}

@Preview
@Composable
fun SignUpScreen_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        SignUpScreen(
            {}, {}
        )
    }
}