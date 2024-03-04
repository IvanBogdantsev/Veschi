package com.acerolla.ui_android

import androidx.lifecycle.ViewModel
import com.acerolla.add_thing_api.ThingsStore
import com.acerolla.add_thing_api.models.CoordinatePoint
import com.acerolla.common.mappers.BaseMapper
import com.acerolla.ui_android.uio.StreetObjectUio
import com.arkivanov.mvikotlin.core.binder.Binder
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.states
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class ThingsViewModel(
    private val store: ThingsStore,
    private val stateMapper: BaseMapper<ThingsStore.State, ThingsUiState>
): ViewModel() {

    private val _screenState = MutableStateFlow(ThingsUiState())
    val screenState: StateFlow<ThingsUiState>
        get() = _screenState.asStateFlow()

    private val _selectedStreetObjectFlow: MutableSharedFlow<StreetObjectUio> = MutableSharedFlow(
        replay = 1,
        extraBufferCapacity = 0,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val selectedStreetObjectFlow = _selectedStreetObjectFlow
        .asSharedFlow()
        .distinctUntilChanged()

    private val binder: Binder

    init {
        binder = bind(Dispatchers.Main.immediate) {
            store.states.map(stateMapper::map) bindTo (::acceptState)
        }
        binder.start()
        getAllStreetObjects()
    }

    fun getStreetObjectsForCoordinates(northwest: CoordinatePoint, southeast: CoordinatePoint) {
        store.accept(
            ThingsStore.Intent.GetStreetObjectsForCoordinates(
                northwest = northwest,
                southwest = southeast
            )
        )
    }

    fun onShowInfoForStreetObjectClick(obj: StreetObjectUio) {
        _selectedStreetObjectFlow.tryEmit(obj)
    }

    private fun getAllStreetObjects() {
        store.accept(ThingsStore.Intent.GetAllStreetObjects)
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