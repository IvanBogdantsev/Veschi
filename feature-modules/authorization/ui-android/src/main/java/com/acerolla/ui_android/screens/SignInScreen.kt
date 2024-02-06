package com.acerolla.ui_android.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.acerolla.android_design_system.HORIZONTAL_PADDING
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.android_design_system.emptyString
import com.acerolla.android_design_system.stringResource
import com.acerolla.shared_resources.SharedResources
import com.acerolla.ui_android.screens.components.AuthButton
import com.acerolla.ui_android.screens.components.AuthTextInput
import com.acerolla.ui_android.screens.components.DividerArea
import com.acerolla.ui_android.screens.components.SignButtonsArea

@Composable
fun SignInScreen() {
    val context = LocalContext.current
    var phoneOrNumber by remember { mutableStateOf(emptyString()) }
    var password by remember { mutableStateOf(emptyString()) }
    Box(
        modifier = Modifier
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
                text = stringResource(SharedResources.strings.welcome),
                fontFamily = FontFamily(Font(SharedResources.fonts.Inter.regular.fontResourceId)),
                fontSize = 12.sp,
                color = Color(
                    SharedResources.colors.primary2.getColor(context)
                )
            )
            Text(
                modifier = Modifier
                    .padding(top = 6.dp),
                text = stringResource(SharedResources.strings.enter_acc),
                fontFamily = FontFamily(Font(SharedResources.fonts.Inter.bold.fontResourceId)),
                fontSize = 34.sp
            )
            AuthTextInput(
                modifier = Modifier
                    .padding(top = 32.dp),
                value = phoneOrNumber,
                hintTitle = stringResource(id = SharedResources.strings.phone_or_mail),
                onValueChanged = { phoneOrNumber = it },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                )
            )
            AuthTextInput(
                modifier = Modifier
                    .padding(top = 18.dp),
                value = password,
                hintTitle = stringResource(id = SharedResources.strings.password),
                onValueChanged = { password = it },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation()
            )
            ForgotPassword(
                onForgotPasswordClick = {  } // TODO:
            )
            AuthButton(
                title = stringResource(SharedResources.strings.enter),
                onClick = {  } // TODO:
            )
            DividerArea()
            SignButtonsArea(
                onGoogleBtnClick = {  }, // TODO:
                onAppleBtnClick = {  }, // TODO:
            )
        }
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
                .padding(top = 16.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onForgotPasswordClick()
                },
            text = stringResource(id = SharedResources.strings.forgot_pass),
            fontFamily = FontFamily(Font(SharedResources.fonts.Inter.regular.fontResourceId)),
            fontSize = 14.sp,
            color = Color(SharedResources.colors.primary.getColor(LocalContext.current))
        )
    }
}

@Preview
@Composable
fun SignInScreen_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        SignInScreen()
    }
}

@Preview
@Composable
fun SignInScreen_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        SignInScreen()
    }
}