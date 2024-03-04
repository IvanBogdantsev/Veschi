package com.acerolla.add_thing_api

import com.acerolla.add_thing_api.models.CoordinatePoint
import com.acerolla.add_thing_api.models.StreetObjectsForCoordinate
import com.acerolla.add_thing_api.models.ThingsErrorModel
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