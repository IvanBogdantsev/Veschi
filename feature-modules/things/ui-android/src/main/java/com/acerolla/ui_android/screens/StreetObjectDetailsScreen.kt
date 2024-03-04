package com.acerolla.ui_android.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.acerolla.android_design_system.HORIZONTAL_PADDING
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.add_thing_api.models.Category
import com.acerolla.add_thing_api.models.Condition
import com.acerolla.shared_resources.SharedResources
import com.acerolla.ui_android.ThingsViewModel
import com.acerolla.ui_android.screens.components.StreetObjectImagesSlider
import org.koin.compose.koinInject

@Composable
fun StreetObjectDetails(
    onBackBtnClick: () -> Unit
) {
    val viewModel = koinInject<ThingsViewModel>()
    val selectedObject by viewModel.selectedStreetObjectFlow.collectAsStateWithLifecycle(null)
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = HORIZONTAL_PADDING),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                BackBtn(
                    onClick = onBackBtnClick
                )
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp),
                    text = selectedObject?.name ?: "Unknown name",
                    fontFamily = FontFamily(Font(SharedResources.fonts.Inter.bold.fontResourceId)),
                    fontSize = 22.sp,
                )
                StreetObjectImagesSlider(
                    sliderList = mutableListOf("", "", "")
                )
                DescriptionBlock(
                    description = selectedObject?.description ?: "No description",
                    vicinity = selectedObject?.vicinity ?: "No vicinity",
                    condition = selectedObject?.condition,
                    category = selectedObject?.category
                )
                Spacer(
                    modifier = Modifier
                        .height(80.dp)
                )
            }
            ButtonBlock(
                onGetObjectClick = {}
            )
        }
    }
}

@Composable
private fun ButtonBlock(
    onGetObjectClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Button(
            modifier = Modifier
                .width(130.dp),
            onClick = onGetObjectClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(
                    SharedResources.colors.primary.getColor(LocalContext.current)
                ),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(25.dp),
            contentPadding = PaddingValues(
                start = 5.dp,
                top = 4.dp,
                end = 17.dp,
                bottom = 4.dp,
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    modifier = Modifier,
                    painter = painterResource(
                        id = SharedResources.images.ok_icon.drawableResId
                    ),
                    contentDescription = null,
                )
                Text(
                    text = stringResource(id = SharedResources.strings.get_street_object.resourceId),
                    fontFamily = FontFamily(Font(SharedResources.fonts.Inter.medium.fontResourceId)),
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun DescriptionBlock(
    description: String,
    vicinity: String,
    condition: Condition?,
    category: Category?
) {
    Column(
        modifier = Modifier
            .padding()
            .fillMaxWidth()
    ) {
        // Description
        Text(
            modifier = Modifier,
            text = stringResource(id = SharedResources.strings.description.resourceId),
            fontFamily = FontFamily(Font(SharedResources.fonts.Inter.bold.fontResourceId)),
            fontSize = 18.sp,
        )
        Text(
            modifier = Modifier
                .padding(top = 10.dp),
            text = description,
            fontFamily = FontFamily(Font(SharedResources.fonts.Inter.regular.fontResourceId)),
            fontSize = 14.sp,
        )
        // Vicinity
        Text(
            modifier = Modifier
                .padding(top = 20.dp),
            text = stringResource(id = SharedResources.strings.vicinity.resourceId),
            fontFamily = FontFamily(Font(SharedResources.fonts.Inter.bold.fontResourceId)),
            fontSize = 18.sp,
        )
        Text(
            modifier = Modifier
                .padding(top = 10.dp),
            text = vicinity,
            fontFamily = FontFamily(Font(SharedResources.fonts.Inter.regular.fontResourceId)),
            fontSize = 14.sp,
        )
        // Category and Condition
        if (category != null) {
            Row(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = SharedResources.strings.category.resourceId),
                    fontFamily = FontFamily(Font(SharedResources.fonts.Inter.bold.fontResourceId)),
                    fontSize = 16.sp,
                )
                Text(
                    modifier = Modifier,
                    text = getCategoryString(category),
                    fontFamily = FontFamily(Font(SharedResources.fonts.Inter.regular.fontResourceId)),
                    fontSize = 14.sp,
                )
            }
        }
        if (condition != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = SharedResources.strings.condition.resourceId),
                    fontFamily = FontFamily(Font(SharedResources.fonts.Inter.bold.fontResourceId)),
                    fontSize = 16.sp,
                )
                Text(
                    modifier = Modifier,
                    text = getConditionString(condition),
                    fontFamily = FontFamily(Font(SharedResources.fonts.Inter.regular.fontResourceId)),
                    fontSize = 14.sp,
                )
            }
        }
    }
}

private fun getCategoryString(category: Category): String {
    return when(category) {
        Category.ELECTRONICS -> "Electronics"
        Category.BOOKS -> "Books"
        Category.FURNITURE -> "Furniture"
        Category.OTHER -> "Other"
    }
}

private fun getConditionString(condition: Condition): String {
    return when(condition) {
        Condition.BRAND_NEW -> "Brand new"
        Condition.GOOD -> "Good"
        Condition.EXCELLENT -> "Excellent"
        Condition.FAIR -> "Fair"
        Condition.LIKE_NEW -> "Like new"
        Condition.POOR -> "Poor"
        Condition.VERY_GOOD -> "Very good"
    }
}

@Composable
private fun BackBtn(
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = Modifier
            .padding(top = 20.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onClick() },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null)
        Text(
            modifier = Modifier,
            text = stringResource(id = SharedResources.strings.back.resourceId),
            fontFamily = FontFamily(Font(SharedResources.fonts.Inter.medium.fontResourceId)),
            fontSize = 15.sp,
        )
    }
}

@Preview
@Composable
private fun StreetObjectDetails_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        StreetObjectDetails(
            onBackBtnClick = {}
        )
    }
}

@Preview
@Composable
private fun StreetObjectDetails_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        StreetObjectDetails(
            onBackBtnClick = {}
        )
    }
}