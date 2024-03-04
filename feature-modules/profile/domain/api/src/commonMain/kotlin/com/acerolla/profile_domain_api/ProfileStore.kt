package com.acerolla.profile_domain_api

import com.acerolla.common.ErrorResponse
import com.acerolla.profile_domain_api.models.ProfileErrorModel
import com.arkivanov.mvikotlin.core.store.Store

interface ProfileStore: Store<ProfileStore.Intent, ProfileStore.State, ProfileStore.Label> {

    data class State(
        val isSuccessfullySigned: Boolean = false,
        val isLoading: Boolean = false,
        val error: ProfileErrorModel<ErrorResponse>? = null
    )

    data class Label(
        val toastString: String
    )

    sealed interface Intent {

        data object Logout: Intent
    }
}