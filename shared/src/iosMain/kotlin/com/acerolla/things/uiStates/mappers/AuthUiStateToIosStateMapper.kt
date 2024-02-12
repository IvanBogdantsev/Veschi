package com.acerolla.things.uiStates.mappers

import com.acerolla.api.AuthStore
import com.acerolla.common.mappers.BaseMapper
import com.acerolla.things.uiStates.authorization.AuthUiStateiOS

class AuthUiStateToIosStateMapper: BaseMapper<AuthStore.State, AuthUiStateiOS> {

    override fun map(from: AuthStore.State): AuthUiStateiOS {
        return AuthUiStateiOS(
            from.isSuccessfullySigned,
            from.isLoading,
            from.error
        )
    }
}