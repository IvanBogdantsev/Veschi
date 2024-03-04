package com.acerolla.things.features.authorization

import com.acerolla.add_thing_api.AuthStore
import com.acerolla.common.mappers.BaseMapper
import com.acerolla.things.utils.ErrorModeliOS

class AuthUiStateToIosStateMapper: BaseMapper<AuthStore.State, AuthUiStateiOS> {

    override fun map(from: AuthStore.State): AuthUiStateiOS {
        return AuthUiStateiOS(
            from.isSuccessfullySigned,
            from.isLoading,
            ErrorModeliOS(
                from.error?.code,
                from.error?.msg
            )
        )
    }
}