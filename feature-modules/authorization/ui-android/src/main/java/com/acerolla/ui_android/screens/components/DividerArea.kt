package com.acerolla.ui_android.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.acerolla.android_design_system.stringResource
import com.acerolla.shared_resources.SharedResources

@Composable
fun DividerArea() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier
                .padding(end = 7.dp)
                .weight(1f),
            thickness = 1.dp,
            color = Color(
                SharedResources.colors.primary2.getColor(context)
            )
        )
        Text(
            text = stringResource(id = SharedResources.strings.or),
            fontFamily = FontFamily(Font(SharedResources.fonts.Inter.regular.fontResourceId)),
            fontSize = 14.sp,
            color = Color(
                SharedResources.colors.primary.getColor(context)
            )
        )
        Divider(
            modifier = Modifier
                .padding(start = 7.dp)
                .weight(1f),
            thickness = 1.dp,
            color = Color(
                SharedResources.colors.primary2.getColor(context)
            )
        )
    }
}