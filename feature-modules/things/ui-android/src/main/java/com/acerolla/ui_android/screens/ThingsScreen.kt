package com.acerolla.ui_android.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.acerolla.add_thing_api.models.StreetObject
import com.acerolla.ui_android.ThingsViewModel
import com.acerolla.ui_android.mappers.toUio
import com.acerolla.ui_android.screens.components.MapBoxMap
import com.acerolla.ui_android.screens.components.StreetObjectInfoDialog
import com.acerolla.ui_android.uio.StreetObjectUio
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import org.koin.compose.koinInject

@Composable
fun ThingsScreen(
    onWatchMoreClick: () -> Unit
) {
    val viewModel = koinInject<ThingsViewModel>()
    val uiState = viewModel.screenState.collectAsStateWithLifecycle()
    var showStreetObjectInfo: StreetObjectUio? by remember { mutableStateOf(null) }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.BottomCenter
        ) {
            MapBoxMap(
                modifier = Modifier
                    .fillMaxSize(),
                mapPoints = getStreetObjectsList(uiState.value.gotStreetObjects?.objects),
                onStreetObjectClick = { streetObject ->
                    showStreetObjectInfo = streetObject
                }
            )
            ShowStreetObjectInfo(
                streetObject = showStreetObjectInfo,
                onCloseBtnClick = {
                    showStreetObjectInfo = null
                },
                onWatchMoreClick = {
                    viewModel.onShowInfoForStreetObjectClick(it)
                    onWatchMoreClick()
                }
            )
        }
    }
}

private fun getStreetObjectsList(list: List<StreetObject>?): PersistentList<StreetObjectUio> {
    return list?.map { it.toUio() }?.toPersistentList() ?: persistentListOf()
}

@Composable
private fun ShowStreetObjectInfo(
    streetObject: StreetObjectUio?,
    onCloseBtnClick: () -> Unit,
    onWatchMoreClick: (StreetObjectUio) -> Unit
) {
    if (streetObject != null) {
        StreetObjectInfoDialog(
            modifier = Modifier
                .padding(bottom = 20.dp),
            name = streetObject.name,
            image = streetObject.images.first(),
            description = streetObject.vicinity,
            onCloseBtnClick = onCloseBtnClick,
            onWatchMoreClick = {
                onWatchMoreClick(streetObject)
            }
        )
    }
}