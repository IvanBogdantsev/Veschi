package com.acerolla.ui_android

import androidx.lifecycle.ViewModel
import com.acerolla.add_thing_domain_api.AddThingStore
import com.acerolla.add_thing_domain_api.model.NewStreetObject
import com.acerolla.ui_android.mappers.toUiState
import com.acerolla.ui_android.uio.AddThingPointUio
import com.arkivanov.mvikotlin.core.binder.Binder
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.states
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class AddThingViewModel(
    private val store: AddThingStore
): ViewModel() {

    private val _addedMapPoint: MutableStateFlow<AddThingPointUio?> = MutableStateFlow(null)
    val addedMapPoint: Flow<AddThingPointUio?> = _addedMapPoint
        .asStateFlow()

    private val _screenState = MutableStateFlow(AddThingUiState())
    val screenState: StateFlow<AddThingUiState>
        get() = _screenState.asStateFlow()

    private val binder: Binder

    init {
        binder = bind(Dispatchers.Main.immediate) {
            store.states.map { it.toUiState() } bindTo (::acceptState)
        }
        binder.start()
    }

    fun setNewMapPoint(point: AddThingPointUio) {
        _addedMapPoint.tryEmit(point)
    }

    fun clearAddedMapPoint() {
        _addedMapPoint.tryEmit(null)
    }

    fun addNewStreetObject(obj: NewStreetObject) {
        store.accept(AddThingStore.Intent.AddNewStreetObject(obj))
    }

    fun clearState() {
        _screenState.tryEmit(AddThingUiState())
    }

    private fun acceptState(state: AddThingUiState) {
        _screenState.tryEmit(state)
    }

    override fun onCleared() {
        super.onCleared()
        binder.stop()
        store.dispose()
    }
}