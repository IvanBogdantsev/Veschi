package com.acerolla.ui_android.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.acerolla.android_design_system.ROUND_CORNER_RADIUS
import com.acerolla.android_design_system.ThingsAppTheme
import com.acerolla.android_design_system.stringResource
import com.acerolla.common.stringToBitMap
import com.acerolla.shared_resources.SharedResources

@Composable
fun StreetObjectInfoDialog(
    modifier: Modifier = Modifier,
    name: String?,
    image: String,
    description: String,
    onCloseBtnClick: () -> Unit,
    onWatchMoreClick: () -> Unit
) {
    val bitmap = stringToBitMap(image)
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .height(130.dp)
            .clip(RoundedCornerShape(ROUND_CORNER_RADIUS))
            .background(Color.White)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray.copy(alpha = 0.5f))
                    .weight(0.4f)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    model = bitmap,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Image(
                    modifier = Modifier
                        .padding(top = 6.dp, start = 6.dp)
                        .clickable {
                            onCloseBtnClick()
                        },
                    painter = painterResource(
                        id = SharedResources.images.close_btn.drawableResId
                    ),
                    contentDescription = null,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .weight(0.6f),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column {
                    Text(
                        modifier = Modifier
                            .padding(top = 6.dp),
                        text = name ?: "Unknown street object",
                        fontFamily = FontFamily(Font(SharedResources.fonts.Inter.bold.fontResourceId)),
                        fontSize = 15.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        modifier = Modifier,
                        text = description,
                        fontFamily = FontFamily(Font(SharedResources.fonts.Inter.regular.fontResourceId)),
                        fontSize = 13.sp,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            onWatchMoreClick()
                        },
                    text = stringResource(id = SharedResources.strings.watch_more),
                    fontFamily = FontFamily(Font(SharedResources.fonts.Inter.semiBold.fontResourceId)),
                    fontSize = 13.sp,
                    color = Color(SharedResources.colors.primary.getColor(LocalContext.current)),
                    textDecoration = TextDecoration.Underline
                )
            }

        }
    }
}

@Preview
@Composable
private fun StreetObjectInfoDialog_Light_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = false
    ) {
        StreetObjectInfoDialog(
            name = "Lorem ipsum dolor sit amet amet amet",
            image = "",
            description = "Lorem ipsum dolor sit amet",
            onCloseBtnClick = {},
            onWatchMoreClick = {}
        )
    }
}

@Preview
@Composable
private fun StreetObjectInfoDialog_Dark_Theme_Preview() {
    ThingsAppTheme(
        darkTheme = true
    ) {
        StreetObjectInfoDialog(
            name = "Lorem ipsum",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            image = "",
            onCloseBtnClick = {},
            onWatchMoreClick = {}
        )
    }
}