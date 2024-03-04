package com.acerolla.impl

import com.acerolla.profile_domain_api.ProfileStore
import com.acerolla.profile_domain_api.models.ProfileErrorModel
import com.arkivanov.mvikotlin.core.store.Reducer

internal class ProfileReducer : Reducer<ProfileStore.State, ProfileStoreFactory.Message> {

    override fun ProfileStore.State.reduce(
        msg: ProfileStoreFactory.Message,
    ) = when (msg) {
        is ProfileStoreFactory.Message.SuccessfullySigned -> copy(
            isSuccessfullySigned = true,
            isLoading = false,
            error = null
        )
        is ProfileStoreFactory.Message.SetLoading -> copy(
            isSuccessfullySigned = false,
            isLoading = true,
            error = null
        )
        is ProfileStoreFactory.Message.SetHttpError -> copy(
            isSuccessfullySigned = false,
            isLoading = false,
            error = ProfileErrorModel(msg.error.code, msg.error.errorBody, "Http exception")
        )
        is ProfileStoreFactory.Message.SetNetworkError -> copy(
            isSuccessfullySigned = false,
            isLoading = false,
            error = ProfileErrorModel(msg = "Network exception")
        )
        is ProfileStoreFactory.Message.SetSerializationError -> copy(
            isSuccessfullySigned = false,
            isLoading = false,
            error = ProfileErrorModel(msg = "Serialization exception")
        )
    }
}