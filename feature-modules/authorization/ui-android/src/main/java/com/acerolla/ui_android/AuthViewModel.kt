package com.acerolla.ui_android

import androidx.lifecycle.ViewModel
import com.acerolla.api.AuthStore
import com.acerolla.common.mappers.BaseMapper
import com.arkivanov.mvikotlin.core.binder.Binder
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.states
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class AuthViewModel(
    private val store: AuthStore,
    private val stateMapper: BaseMapper<AuthStore.State, UiState>
): ViewModel() {

    private val _screenState = MutableStateFlow(UiState())
    val screenState: StateFlow<UiState>
        get() = _screenState.asStateFlow()

    private val binder: Binder

    init {
        binder = bind(Dispatchers.Main.immediate) {
            store.states.map(stateMapper::map) bindTo (::acceptState)
        }
        binder.start()
        moveToSignUpBtnClick()
    }

    fun moveToSignInBtnClick() {
        store.accept(AuthStore.Intent.MoveToSignIn)
    }

    fun moveToSignUpBtnClick() {
        store.accept(AuthStore.Intent.MoveToSignUp)
    }

    private fun acceptState(state: UiState) {
        _screenState.tryEmit(state)
    }

    override fun onCleared() {
        super.onCleared()
        binder.stop()
        store.dispose()
    }
}