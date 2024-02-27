package com.acerolla.ui_android.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.acerolla.ui_android.ThingsViewModel
import com.acerolla.ui_android.screens.components.MapBoxMap
import org.koin.androidx.compose.koinViewModel

@Composable
fun ThingsScreen() {
    val vm = koinViewModel<ThingsViewModel>()
    val state = vm.screenState.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MapBoxMap(
                modifier = Modifier
                    .fillMaxSize(),
                mapPoints = state.value.gotStreetObjects?.objects ?: emptyList()
            )
        }
    }
}