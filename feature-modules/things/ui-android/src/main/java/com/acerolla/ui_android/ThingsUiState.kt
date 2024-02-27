package com.acerolla.ui_android

import com.acerolla.api.models.StreetObjectsForCoordinate
import com.acerolla.api.models.ThingsErrorModel
import com.acerolla.common.ErrorResponse

data class ThingsUiState(
    val gotStreetObjects: StreetObjectsForCoordinate? = null,
    val isLoading: Boolean = false,
    val error: ThingsErrorModel<ErrorResponse>? = null
)
