package com.acerolla.ui_android

import com.acerolla.add_thing_api.models.StreetObjectsForCoordinate
import com.acerolla.add_thing_api.models.ThingsErrorModel
import com.acerolla.common.ErrorResponse

data class ThingsUiState(
    val gotStreetObjects: StreetObjectsForCoordinate? = null,
    val isLoading: Boolean = false,
    val error: ThingsErrorModel<ErrorResponse>? = null
)
