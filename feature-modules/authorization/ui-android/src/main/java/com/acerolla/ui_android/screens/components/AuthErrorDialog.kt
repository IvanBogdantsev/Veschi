package com.acerolla.ui_android.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.acerolla.android_design_system.HORIZONTAL_PADDING
import com.acerolla.android_design_system.ROUND_CORNER_RADIUS
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.android_design_system.emptyString
import com.acerolla.android_design_system.stringResource
import com.acerolla.shared_resources.SharedResources

@Composable
fun AuthErrorDialog(
    shouldShow: Boolean = true,
    msg: String? = emptyString(),
    dismissOnBackPressed: Boolean = true,
    dismissOnClickOutside: Boolean = true, 
    onDismissClicked: () -> Unit = {}
) {
    if (!shouldShow) return
    val interactionSource = remember { MutableInteractionSource() }
    Dialog(
        properties = DialogProperties(
            dismissOnBackPress = dismissOnBackPressed,
            dismissOnClickOutside = dismissOnClickOutside
        ),
        onDismissRequest = onDismissClicked
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(ROUND_CORNER_RADIUS))
                .width(250.dp)
                .wrapContentHeight()
                .background(color = MaterialTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .padding(start = HORIZONTAL_PADDING)
                        .padding(bottom = 10.dp),
                    text = stringResource(id = SharedResources.strings.error_occurred),
                    fontFamily = FontFamily(Font(SharedResources.fonts.Inter.medium.fontResourceId)),
                    fontSize = 14.sp,
                    color = Color(SharedResources.colors.primary.getColor(LocalContext.current))
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = HORIZONTAL_PADDING),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = msg ?: emptyString(),
                        fontFamily = FontFamily(Font(SharedResources.fonts.Inter.regular.fontResourceId)),
                        fontSize = 15.sp,
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .padding(vertical = 13.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) {
                                onDismissClicked()
                            },
                        text = stringResource(id = SharedResources.strings.try_again),
                        fontFamily = FontFamily(Font(SharedResources.fonts.Inter.bold.fontResourceId)),
                        fontSize = 16.sp,
                        color = Color(SharedResources.colors.primary.getColor(LocalContext.current))
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AuthErrorDialog_Light_Theme() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        AuthErrorDialog(
            msg = stringResource(id = SharedResources.strings.lorem_ipsum_100)
        )
    }
}

@Preview
@Composable
private fun AuthErrorDialog_Dark_Theme() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        AuthErrorDialog(
            msg = stringResource(id = SharedResources.strings.lorem_ipsum_short)
        )
    }
}