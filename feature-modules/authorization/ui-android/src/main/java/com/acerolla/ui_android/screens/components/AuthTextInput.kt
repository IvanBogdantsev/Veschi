package com.acerolla.ui_android.screens.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.acerolla.android_design_system.ROUND_CORNER_RADIUS
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.shared_resources.SharedResources

@Composable
fun AuthTextInput(
    modifier: Modifier,
    value: String,
    hintTitle: String,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val context = LocalContext.current
    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color(SharedResources.colors.input_border_color.getColor(context)),
                shape = RoundedCornerShape(ROUND_CORNER_RADIUS)
            )
            .height(40.dp)
            .padding(start = 16.dp),
        value = value,
        onValueChange = onValueChanged,
        decorationBox = { innerTextField ->
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier.fillMaxHeight()
            ) {
                if (value.isEmpty()) {
                    Text(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentHeight(align = Alignment.CenterVertically),
                        text = hintTitle,
                        color = Color(
                            SharedResources.colors.primary2.getColor(context)
                        ),
                        fontSize = 16.sp,
                    )
                }
                innerTextField()
            }
        },
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        textStyle = TextStyle(
            fontFamily = FontFamily(Font(SharedResources.fonts.Inter.regular.fontResourceId)),
            fontSize = 16.sp,
            color = Color.Black,
            textAlign = TextAlign.Justify
        ),
        singleLine = true,
        cursorBrush = SolidColor(Color(SharedResources.colors.primary.getColor(context))),
    )
}

@Preview
@Composable
private fun AuthTextInput_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        AuthTextInput(
            modifier = Modifier,
            value = "Почта",
            hintTitle = "Пароль",
            onValueChanged = { _ -> },
            keyboardOptions = KeyboardOptions(),
            visualTransformation = VisualTransformation.None
        )
    }
}

@Preview
@Composable
private fun AuthTextInput_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        AuthTextInput(
            modifier = Modifier,
            value = "Почта",
            hintTitle = "Пароль",
            onValueChanged = { _ -> },
            keyboardOptions = KeyboardOptions(),
            visualTransformation = PasswordVisualTransformation()
        )
    }
}