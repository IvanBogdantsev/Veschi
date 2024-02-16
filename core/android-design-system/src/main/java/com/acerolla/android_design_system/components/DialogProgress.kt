package com.acerolla.android_design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.acerolla.android_design_system.ROUND_CORNER_RADIUS
import com.acerolla.android_design_system.ThingsAppTheme

@Composable
fun DialogProgress(
    shouldShow: Boolean = true,
    dismissOnBackPressed: Boolean = true,
    dismissOnClickOutside: Boolean = true
) {
    if (!shouldShow) return
    Dialog(
        properties = DialogProperties(
            dismissOnBackPress = dismissOnBackPressed,
            dismissOnClickOutside = dismissOnClickOutside
        ),
        onDismissRequest = {  }
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(ROUND_CORNER_RADIUS))
                .size(130.dp)
                .background(color = MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            ProgressIndicator()
        }
    }
}

@Preview
@Composable
private fun DialogProgress_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        DialogProgress()
    }
}

@Preview
@Composable
private fun DialogProgress_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        DialogProgress()
    }
}