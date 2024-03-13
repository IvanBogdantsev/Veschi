package com.acerolla.ui_android.screens.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.acerolla.android_design_system.ROUND_CORNER_RADIUS
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.shared_resources.SharedResources

@Composable
fun AddThingButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        onClick = onClick,
        shape = RoundedCornerShape(ROUND_CORNER_RADIUS),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(
                SharedResources.colors.primary.getColor(LocalContext.current)
            ),
            contentColor = Color.White
        )
    ) {
        Text(
            text = title,
            fontFamily = FontFamily(Font(SharedResources.fonts.Inter.regular.fontResourceId)),
            fontSize = 14.sp,
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun AddThingButton_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        AddThingButton(
            modifier = Modifier,
            title = "Войти",
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun AddThingButton_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        AddThingButton(
            modifier = Modifier,
            title = "Зарегистрироваться",
            onClick = {}
        )
    }
}