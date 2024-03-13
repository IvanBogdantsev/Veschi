package com.acerolla.ui_android

import com.acerolla.add_thing_domain_api.model.AddThingErrorModel
import com.acerolla.common.ErrorResponse

data class AddThingUiState(
    val successfullySigned: Boolean = false,
    val isLoading: Boolean = false,
    val error: AddThingErrorModel<ErrorResponse>? = null
)
