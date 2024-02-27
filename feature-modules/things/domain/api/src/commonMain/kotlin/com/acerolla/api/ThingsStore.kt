package com.acerolla.api

import com.acerolla.api.models.CoordinatePoint
import com.acerolla.api.models.StreetObjectsForCoordinate
import com.acerolla.api.models.ThingsErrorModel
import com.acerolla.common.ErrorResponse
import com.arkivanov.mvikotlin.core.store.Store

interface ThingsStore: Store<ThingsStore.Intent, ThingsStore.State, ThingsStore.Label> {

    data class State(
        val gotStreetObjects: StreetObjectsForCoordinate? = null,
        val isLoading: Boolean = false,
        val error: ThingsErrorModel<ErrorResponse>? = null
    )

    data class Label(
        val toastString: String
    )

    sealed interface Intent {

        data class GetStreetObjectsForCoordinates(
            val northwest: CoordinatePoint,
            val southwest: CoordinatePoint
        ): Intent
        data object GetAllStreetObjects: Intent
    }
}