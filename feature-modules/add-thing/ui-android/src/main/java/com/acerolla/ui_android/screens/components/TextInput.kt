package com.acerolla.ui_android.screens.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import com.acerolla.android_design_system.HORIZONTAL_PADDING
import com.acerolla.android_design_system.ROUND_CORNER_RADIUS
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.android_design_system.emptyString
import com.acerolla.android_design_system.stringResource
import com.acerolla.shared_resources.SharedResources

@Composable
fun TextInput(
    modifier: Modifier,
    value: String,
    hintTitle: String,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    notValidValue: Boolean = false,
    errorText: String = emptyString()
) {
    val errorColor = Color.Red.copy(alpha = 0.55f)
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            modifier = modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = if (notValidValue) errorColor else Color(
                        SharedResources.colors.input_border_color.getColor(
                            context
                        )
                    ),
                    shape = RoundedCornerShape(ROUND_CORNER_RADIUS)
                )
                .height(45.dp),
            value = value,
            onValueChange = onValueChanged,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (value.isEmpty()){
                            Text(
                                text = hintTitle,
                                color = Color(SharedResources.colors.primary2.getColor(context)),
                                fontSize = 16.sp,
                            )
                        }
                        innerTextField()
                    }
                    if (notValidValue) {
                        Icon(
                            modifier = Modifier
                                .padding(end =  16.dp),
                            imageVector = Icons.Filled.Warning,
                            contentDescription = null,
                            tint = errorColor
                        )
                    }
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
        Text(
            modifier = Modifier
                .alpha(
                    if (notValidValue) 1f else 0f
                ),
            text = errorText,
            color = errorColor,
            fontFamily = FontFamily(Font(SharedResources.fonts.Inter.medium.fontResourceId)),
            fontSize = 12.sp
        )
    }

}

@Preview
@Composable
private fun AuthTextInput_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        TextInput(
            modifier = Modifier,
            value = stringResource(id = SharedResources.strings.lorem_ipsum_short),
            hintTitle = stringResource(id = SharedResources.strings.lorem_ipsum_short),
            onValueChanged = { _ -> },
            keyboardOptions = KeyboardOptions(),
            visualTransformation = VisualTransformation.None,
            notValidValue = true,
            errorText = stringResource(id = SharedResources.strings.lorem_ipsum_short)
        )
    }
}

@Preview
@Composable
private fun TextInput_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        TextInput(
            modifier = Modifier,
            value = stringResource(id = SharedResources.strings.lorem_ipsum_short),
            hintTitle = stringResource(id = SharedResources.strings.lorem_ipsum_short),
            onValueChanged = { _ -> },
            keyboardOptions = KeyboardOptions(),
            visualTransformation = PasswordVisualTransformation()
        )
    }
}