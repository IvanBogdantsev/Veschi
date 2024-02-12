package com.acerolla.android_design_system.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.shared_resources.SharedResources

@Composable
fun ProgressIndicator() {
    CircularProgressIndicator(
        modifier = Modifier.size(64.dp),
        color = Color(SharedResources.colors.primary.getColor(LocalContext.current)),
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}

@Preview
@Composable
private fun ProgressIndicator_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        ProgressIndicator()
    }
}

@Preview
@Composable
private fun ProgressIndicator_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        ProgressIndicator()
    }
}