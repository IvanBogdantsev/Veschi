package com.acerolla.ui_android.screens.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.NoOpUpdate
import com.acerolla.android_design_system.ThingsAppTheme
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.animation.flyTo
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager

@Composable
fun MapBoxMap(
    modifier: Modifier = Modifier,
    point: Point? = null
) {
    var pointAnnotationManager: PointAnnotationManager? by remember {
        mutableStateOf(null)
    }
    AndroidView(
        factory = {
            MapView(it).also { mapView ->
                mapView.getMapboxMap().loadStyleUri(Style.TRAFFIC_DAY)
                val annotationApi = mapView.annotations
                pointAnnotationManager = annotationApi.createPointAnnotationManager()
            }
        },
        update = { mapView ->

            val mapBoxMap = mapView.getMapboxMap()
            val cameraOptions = CameraOptions.Builder()
                .zoom(mapBoxMap.cameraState.zoom)
                .center(mapBoxMap.cameraState.center)
                .build()
            val bbox = mapBoxMap.coordinateBoundsForCamera(cameraOptions)


            if (point != null) {
                pointAnnotationManager?.let {
                    it.deleteAll()
                    val pointAnnotationOptions = PointAnnotationOptions()
                        .withPoint(point)

                    it.create(pointAnnotationOptions)
                    mapView.getMapboxMap()
                        .flyTo(CameraOptions.Builder().zoom(16.0).center(point).build())
                }
            }
            NoOpUpdate
        },
        modifier = modifier
    )
}

@Preview
@Composable
private fun MapBoxMap_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        MapBoxMap(
            modifier = Modifier,
            point = null
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
            point = null
        )
    }
}