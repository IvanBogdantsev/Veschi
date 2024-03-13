package com.acerolla.ui_android.screens.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.drawable.toBitmap
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.shared_resources.SharedResources
import com.acerolla.ui_android.uio.StreetObjectUio
import com.google.gson.Gson
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotationGroup
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.attribution.generated.AttributionSettings
import com.mapbox.maps.plugin.compass.generated.CompassSettings
import com.mapbox.maps.plugin.scalebar.generated.ScaleBarSettings
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@OptIn(MapboxExperimental::class)
@Composable
fun MapBoxMap(
    modifier: Modifier = Modifier,
    mapPoints: PersistentList<StreetObjectUio> = persistentListOf(),
    onStreetObjectClick: (StreetObjectUio) -> Unit
) {
    val context = LocalContext.current
    val dr = SharedResources.images.street_object_marker.getDrawable(context)?.toBitmap()!!
    val gson = Gson()
    MapboxMap(
        modifier = modifier.fillMaxSize(),
        compassSettings = CompassSettings.Builder()
            .setEnabled(false)
            .build(),
        scaleBarSettings = ScaleBarSettings.Builder()
            .setEnabled(false)
            .build(),
        attributionSettings = AttributionSettings.Builder()
            .build()
    ) {
        PointAnnotationGroup(
            annotations = mapPoints
                .map {
                    val obj = gson.toJsonTree(it)
                    PointAnnotationOptions()
                        .withPoint(
                            Point.fromLngLat(
                                it.geometry.longitude,
                                it.geometry.latitude
                            )
                        )
                        .withIconImage(dr)
                        .withData(obj)
                },
            onClick = { pointAnnotation ->
                pointAnnotation.getData()?.let { json ->
                    val obj = gson.fromJson(json, StreetObjectUio::class.java)
                    onStreetObjectClick(obj)
                }
                true
            }
        )
    }
}

@Preview
@Composable
private fun MapBoxMap_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        MapBoxMap(
            modifier = Modifier,
            mapPoints = persistentListOf(),
            onStreetObjectClick = {}
        )
    }
}

@Preview
@Composable
private fun MapBoxMap_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        MapBoxMap(
            modifier = Modifier,
            mapPoints = persistentListOf(),
            onStreetObjectClick = {}
        )
    }
}