package com.acerolla.things.uiStates.authorization

import com.acerolla.api.AuthStatePublisher
import com.acerolla.api.AuthStore
import com.acerolla.common.mappers.BaseMapper
import com.acerolla.things.uiStates.BaseUiStatePublisheriOS
import com.acerolla.things.utils.FlowWrapper
import kotlinx.coroutines.flow.map

class AuthStatePublisheriOS(
    private val publisher: AuthStatePublisher,
    private val uiStateMapper: BaseMapper<AuthStore.State, AuthUiStateiOS>
): BaseUiStatePublisheriOS() {

    fun publishState(): FlowWrapper<AuthUiStateiOS> {
        return FlowWrapper(scope, publisher.publishState().map(::mapUiStateToiOSState))
    }

    fun moveToSignIn() {
        publisher.moveToSignIn()
    }

    private fun mapUiStateToiOSState(state: AuthStore.State): AuthUiStateiOS = uiStateMapper.map(state)
}