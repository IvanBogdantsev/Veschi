package com.acerolla.android_design_system

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.acerolla.things.Strings
import dev.icerock.moko.resources.StringResource

@Composable
fun stringResource(id: StringResource, vararg args: Any): String {
    return Strings(LocalContext.current).get(id, args.toList())
}

fun emptyString() = ""