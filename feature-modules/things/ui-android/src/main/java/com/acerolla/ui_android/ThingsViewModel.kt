package com.acerolla.ui_android

import androidx.lifecycle.ViewModel
import com.acerolla.api.ThingsStore
import com.acerolla.api.models.CoordinatePoint
import com.acerolla.common.mappers.BaseMapper
import com.arkivanov.mvikotlin.core.binder.Binder
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.states
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class ThingsViewModel(
    private val store: ThingsStore,
    private val stateMapper: BaseMapper<ThingsStore.State, ThingsUiState>
): ViewModel() {

    private val _screenState = MutableStateFlow(ThingsUiState())
    val screenState: StateFlow<ThingsUiState>
        get() = _screenState.asStateFlow()

    private val binder: Binder

    init {
        binder = bind(Dispatchers.Main.immediate) {
            store.states.map(stateMapper::map) bindTo (::acceptState)
        }
        binder.start()
        getStreetObjectsForCoordinates(
            CoordinatePoint(), CoordinatePoint()
        )
    }

    fun getStreetObjectsForCoordinates(northwest: CoordinatePoint, southeast: CoordinatePoint) {
        store.accept(
            ThingsStore.Intent.GetStreetObjectsForCoordinates(
                northwest = northwest,
                southwest = southeast
            )
        )
    }

    private fun acceptState(state: ThingsUiState) {
        _screenState.tryEmit(state)
    }

    override fun onCleared() {
        super.onCleared()
        binder.stop()
        store.dispose()
    }
}