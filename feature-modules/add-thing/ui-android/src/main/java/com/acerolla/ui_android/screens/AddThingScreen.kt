package com.acerolla.ui_android.screens

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.acerolla.add_thing_domain_api.model.Geometry
import com.acerolla.add_thing_domain_api.model.NewStreetObject
import com.acerolla.android_design_system.HORIZONTAL_PADDING
import com.acerolla.android_design_system.ROUND_CORNER_RADIUS
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.android_design_system.components.DialogProgress
import com.acerolla.android_design_system.components.dashedBorder
import com.acerolla.android_design_system.emptyString
import com.acerolla.android_design_system.stringResource
import com.acerolla.common.bitMapToString
import com.acerolla.common.stringToBitMap
import com.acerolla.shared_resources.SharedResources
import com.acerolla.ui_android.AddThingViewModel
import com.acerolla.ui_android.screens.components.AddThingButton
import com.acerolla.ui_android.screens.components.AddThingErrorDialog
import com.acerolla.ui_android.screens.components.DescriptionTextInput
import com.acerolla.ui_android.screens.components.SuccessfullyAddedDialog
import com.acerolla.ui_android.screens.components.TextInput
import com.acerolla.ui_android.uio.AddThingPointUio
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.plugin.attribution.generated.AttributionSettings
import com.mapbox.maps.plugin.compass.generated.CompassSettings
import com.mapbox.maps.plugin.gestures.generated.GesturesSettings
import com.mapbox.maps.plugin.scalebar.generated.ScaleBarSettings
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import org.koin.compose.koinInject

@Composable
fun AddThingScreen(
    onAddPointClick: () -> Unit
) {
    val vm = koinInject<AddThingViewModel>()
    val state = vm.screenState.collectAsStateWithLifecycle()
    val point = vm.addedMapPoint.collectAsStateWithLifecycle(initialValue = null)
    var photosList by rememberSaveable { mutableStateOf(listOf<String>()) }
    var thingTitle by rememberSaveable { mutableStateOf(emptyString()) }
    var thingDescription by rememberSaveable { mutableStateOf(emptyString()) }
    var showProgress by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var showSuccess by remember { mutableStateOf(false) }
    var showEmptyTitleToast by remember { mutableStateOf(false) }
    showProgress = state.value.isLoading
    showError = state.value.error != null
    showSuccess = state.value.successfullySigned
    if (showSuccess) {
        photosList = persistentListOf()
        thingDescription = emptyString()
        thingTitle = emptyString()
        vm.clearAddedMapPoint()
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = HORIZONTAL_PADDING)
                .padding(top = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(id = SharedResources.strings.add_thing),
                fontFamily = FontFamily(Font(SharedResources.fonts.Inter.medium.fontResourceId)),
                fontSize = 15.sp
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                text = stringResource(id = SharedResources.strings.title),
                fontFamily = FontFamily(Font(SharedResources.fonts.Inter.medium.fontResourceId)),
                fontSize = 15.sp
            )
            TextInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                value = thingTitle,
                hintTitle = stringResource(id = SharedResources.strings.thing_title),
                onValueChanged = {
                    thingTitle = it
                },
                keyboardOptions = KeyboardOptions.Default
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = SharedResources.strings.title_photo),
                fontFamily = FontFamily(Font(SharedResources.fonts.Inter.medium.fontResourceId)),
                fontSize = 15.sp
            )
            ThingPhotos(
                photos = photosList.toPersistentList(),
                onNewBitmapLoaded = { newImage: String ->
                    photosList = mutableListOf<String>().apply {
                        addAll(photosList)
                        add(newImage)
                    }
                }
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                text = stringResource(id = SharedResources.strings.address),
                fontFamily = FontFamily(Font(SharedResources.fonts.Inter.medium.fontResourceId)),
                fontSize = 15.sp
            )
            AddedObjectMapPoint(
                addedPoint = point.value,
                onAddPointClick = onAddPointClick
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                text = stringResource(id = SharedResources.strings.thing_description),
                fontFamily = FontFamily(Font(SharedResources.fonts.Inter.medium.fontResourceId)),
                fontSize = 15.sp
            )
            DescriptionTextInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(top = 5.dp),
                value = thingDescription,
                hintTitle = stringResource(id = SharedResources.strings.description),
                onValueChanged = {
                    thingDescription = it
                },
                keyboardOptions = KeyboardOptions.Default
            )
            AddThingButton(
                title = stringResource(id = SharedResources.strings.add_thing_btn_text),
                onClick = {
                    if (thingTitle != emptyString() && photosList.isNotEmpty() && point != null) {
                        vm.addNewStreetObject(
                            NewStreetObject(
                                name = thingTitle ?: "No title",
                                description = thingDescription ?: "No Description",
                                geometry = Geometry(
                                    point.value?.latitude ?: 0.0,
                                    point.value?.longitude ?: 0.0,
                                    point.value?.accuracy ?: 0.0,
                                ),
                                images = photosList
                            )
                        )
                    } else {
                        showEmptyTitleToast = true
                    }
                }
            )
            Spacer(
                modifier = Modifier
                    .height(50.dp)
            )
        }
        DialogProgress(
            shouldShow = showProgress,
            dismissOnBackPressed = false,
            dismissOnClickOutside = false
        )
        AddThingErrorDialog(
            shouldShow = showError,
            msg = state.value.error?.body?.message,
            dismissOnBackPressed = true,
            dismissOnClickOutside = true,
            onDismissClicked = {
                vm.clearState()
            }
        )
        SuccessfullyAddedDialog(
            shouldShow = showSuccess,
            dismissOnBackPressed = true,
            dismissOnClickOutside = true,
            onDismissClicked = {
                vm.clearState()
            }
        )
        if (showEmptyTitleToast) {
            Toast.makeText(LocalContext.current, stringResource(id = SharedResources.strings.fill_necessary_fields_error), Toast.LENGTH_SHORT).show()
            showEmptyTitleToast = false
        }
    }
}

@Composable
private fun ThingPhotos(
    photos: PersistentList<String>,
    onNewBitmapLoaded: (String) -> Unit
) {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current
    var bitmap: Bitmap?
    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap = MediaStore.Images
                    .Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder
                    .createSource(context.contentResolver, it)
                bitmap = ImageDecoder.decodeBitmap(source)
            }
            onNewBitmapLoaded(
                bitMapToString(bitmap!!)
            )
            imageUri = uri
        }
    }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        state = rememberLazyListState(),
        horizontalArrangement = Arrangement.Start
    ) {
        items(photos) {
            ThingPhoto(image = it)
        }
        item {
            AddThingPhotoButton(
                onClick = {
                    launcher.launch("image/*")
                }
            )
        }
    }
}

@Composable
private fun ThingPhoto(
    image: String
) {
    val bitmap = stringToBitMap(image)
    Box(
        modifier = Modifier
            .padding(top = 15.dp, end = 10.dp)
            .width(200.dp)
            .height(120.dp)
            .clip(RoundedCornerShape(ROUND_CORNER_RADIUS)),
        contentAlignment = Alignment.Center
    ) {
        if (bitmap == null) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray),
                painter = painterResource(
                    id = SharedResources.images.google_icon.drawableResId
                ),
                contentDescription = null,
            )
        } else {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray),
                model = bitmap,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}

@OptIn(MapboxExperimental::class)
@Composable
private fun AddedObjectMapPoint(
    addedPoint: AddThingPointUio? = null,
    onAddPointClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val dr = SharedResources.images.street_object_marker
        .getDrawable(LocalContext.current)?.toBitmap()!!
    if (addedPoint != null) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
                .clickable {
                    onAddPointClick()
                }
                .height(140.dp)
                .clip(RoundedCornerShape(ROUND_CORNER_RADIUS)),
        ) {
            val context = LocalContext.current
            MapboxMap(
                modifier = Modifier
                    .clickable {
                        onAddPointClick()
                    }
                    .fillMaxSize(),
                compassSettings = CompassSettings.Builder()
                    .setEnabled(false)
                    .build(),
                scaleBarSettings = ScaleBarSettings.Builder()
                    .setEnabled(false)
                    .build(),
                gesturesSettings = GesturesSettings.Builder()
                    .setPitchEnabled(false)
                    .setRotateEnabled(false)
                    .setScrollEnabled(false)
                    .setPinchScrollEnabled(false)
                    .setPinchToZoomEnabled(false)
                    .setQuickZoomEnabled(false)
                    .setRotateDecelerationEnabled(false)
                    .setScrollDecelerationEnabled(false)
                    .setSimultaneousRotateAndPinchToZoomEnabled(false)
                    .setDoubleTapToZoomInEnabled(false)
                    .build(),
                attributionSettings = AttributionSettings.Builder()
                    .build(),
                mapInitOptionsFactory = {
                    val opt = MapInitOptions(context)
                    opt.cameraOptions = CameraOptions.Builder()
                        .center(Point.fromLngLat(addedPoint.longitude, addedPoint.latitude))
                        .zoom(15.0)
                        .build()
                    opt
                }
            ) {
                PointAnnotation(
                    point = Point
                        .fromLngLat(addedPoint.longitude, addedPoint.latitude),
                    iconImageBitmap = dr
                )
            }
        }
    } else {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onAddPointClick()
                },
            text = stringResource(id = SharedResources.strings.add_point),
            fontFamily = FontFamily(Font(SharedResources.fonts.Inter.medium.fontResourceId)),
            fontSize = 15.sp,
            textDecoration = TextDecoration.Underline,
            color = Color(
                SharedResources.colors.primary.getColor(LocalContext.current)
            )
        )
    }
}

@Composable
private fun AddThingPhotoButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 15.dp)
            .width(200.dp)
            .height(120.dp)
            .clickable {
                onClick()
            }
            .dashedBorder(
                strokeWidth = 1.dp,
                color = Color(
                    SharedResources.colors.primary.getColor(LocalContext.current)
                ).copy(alpha = 0.7f),
                cornerRadiusDp = ROUND_CORNER_RADIUS
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = SharedResources.strings.add_thing),
            fontFamily = FontFamily(Font(SharedResources.fonts.Inter.medium.fontResourceId)),
            fontSize = 15.sp,
            textDecoration = TextDecoration.Underline,
            color = Color(
                SharedResources.colors.primary.getColor(LocalContext.current)
            ).copy(alpha = 0.7f)
        )
    }
}

@Preview
@Composable
private fun AddThingScreen_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        AddThingScreen(
            onAddPointClick = {}
        )
    }
}

@Preview
@Composable
private fun AddThingScreen_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        AddThingScreen(
            onAddPointClick = {}
        )
    }
}