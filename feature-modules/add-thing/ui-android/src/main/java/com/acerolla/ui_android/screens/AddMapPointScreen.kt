package com.acerolla.ui_android.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.ui_android.AddThingViewModel
import com.acerolla.ui_android.uio.AddThingPointUio
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.plugin.attribution.generated.AttributionSettings
import com.mapbox.maps.plugin.compass.generated.CompassSettings
import com.mapbox.maps.plugin.scalebar.generated.ScaleBarSettings
import org.koin.compose.koinInject

@OptIn(MapboxExperimental::class)
@Composable
fun AddMapPointScreen(
    onSuccessullyAdded: () -> Unit
) {
    val vm = koinInject<AddThingViewModel>()
    MapboxMap(
        modifier = Modifier
            .fillMaxSize(),
        onMapClickListener = { point: Point ->
            vm.setNewMapPoint(AddThingPointUio(point.latitude(), point.longitude(), point.altitude()))
            onSuccessullyAdded()
            true
        },
        compassSettings = CompassSettings.Builder()
            .setEnabled(false)
            .build(),
        scaleBarSettings = ScaleBarSettings.Builder()
            .setEnabled(false)
            .build(),
        attributionSettings = AttributionSettings.Builder()
            .build(),
    )
}

@Preview
@Composable
private fun AddMapPointScreen_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        AddMapPointScreen(
            onSuccessullyAdded = {}
        )
    }
}

@Preview
@Composable
private fun AddMapPointScreen_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        AddMapPointScreen(
            onSuccessullyAdded = {}
        )
    }
}