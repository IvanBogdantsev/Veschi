package com.acerolla.things.features.things

import com.acerolla.things.features.things.models.StreetObjectsForCoordinatesIOS
import com.acerolla.things.utils.ErrorModeliOS

data class ThingsUiStateiOS(
    val gotStreetObjects: StreetObjectsForCoordinatesIOS? = null,
    val isLoading: Boolean = false,
    val error: ErrorModeliOS? = null
)
