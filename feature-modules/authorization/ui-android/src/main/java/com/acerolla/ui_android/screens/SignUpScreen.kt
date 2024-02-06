package com.acerolla.ui_android.screens

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
import com.acerolla.ui_android.screens.components.RoundCheckbox
import com.acerolla.ui_android.screens.components.SignButtonsArea

@Composable
fun SignUpScreen() {
    val context = LocalContext.current
    var username by remember { mutableStateOf(emptyString()) }
    var email by remember { mutableStateOf(emptyString()) }
    var password by remember { mutableStateOf(emptyString()) }
    var repeatPassword by remember { mutableStateOf(emptyString()) }
    var isPoliticsAgreed by remember { mutableStateOf(false) }
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
                text = stringResource(SharedResources.strings.register),
                fontFamily = FontFamily(Font(SharedResources.fonts.Inter.bold.fontResourceId)),
                fontSize = 32.sp
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
                )
            )
            AuthTextInput(
                modifier = Modifier
                    .padding(top = 18.dp),
                value = email,
                hintTitle = stringResource(id = SharedResources.strings.phone_or_mail),
                onValueChanged = { email = it },
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
                    keyboardType = KeyboardType.Text
                ),
                visualTransformation = PasswordVisualTransformation()
            )
            AuthTextInput(
                modifier = Modifier
                    .padding(top = 18.dp),
                value = repeatPassword,
                hintTitle = stringResource(id = SharedResources.strings.repeat_pas),
                onValueChanged = { repeatPassword = it },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                visualTransformation = PasswordVisualTransformation()
            )
            ConfPoliticsArea(
                checked = isPoliticsAgreed,
                onClick = { isPoliticsAgreed = !isPoliticsAgreed }
            )
            AuthButton(
                title = stringResource(id = SharedResources.strings.register),
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
private fun ConfPoliticsArea(
    checked: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
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
fun SignUpScreen_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        SignUpScreen()
    }
}

@Preview
@Composable
fun SignUpScreen_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        SignUpScreen()
    }
}