package com.acerolla.ui_android.screens.components

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.drawable.toBitmap
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.api.models.StreetObject
import com.acerolla.shared_resources.SharedResources
import com.mapbox.geojson.Point
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotationGroup
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions

@OptIn(MapboxExperimental::class)
@Composable
fun MapBoxMap(
    modifier: Modifier = Modifier,
    mapPoints: List<StreetObject> = emptyList()
) {
    val context = LocalContext.current
    val dr = SharedResources.images.street_object_marker.getDrawable(context)?.toBitmap()!!
    MapboxMap(modifier = modifier.fillMaxSize()) {
        PointAnnotationGroup(
            annotations = mapPoints
                .map {
                    Point.fromLngLat(it.geometry.longitude, it.geometry.latitude)
                }
                .map {
                    PointAnnotationOptions()
                        .withPoint(it)
                        .withIconImage(dr)
                },
            onClick = {
                Toast.makeText(
                    context,
                    "Clicked on Circle Annotation Cluster item: $it",
                    Toast.LENGTH_SHORT
                ).show()
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
            mapPoints = emptyList()
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
            mapPoints = emptyList()
        )
    }
}