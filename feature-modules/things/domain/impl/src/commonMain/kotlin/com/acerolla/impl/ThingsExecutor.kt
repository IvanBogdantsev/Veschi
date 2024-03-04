package com.acerolla.impl

import com.acerolla.add_thing_api.StreetObjectsRepository
import com.acerolla.add_thing_api.ThingsStore
import com.acerolla.add_thing_api.models.CoordinatePoint
import com.acerolla.common.ApiResponse
import com.acerolla.common.BaseExecutor

internal class ThingsExecutor(
    private val repository: StreetObjectsRepository,
) : BaseExecutor<ThingsStore.Intent, Nothing, ThingsStore.State, ThingsStoreFactory.Message, Nothing>() {

    override suspend fun suspendExecuteIntent(
        intent: ThingsStore.Intent,
        getState: () -> ThingsStore.State,
    ) = when (intent) {
        is ThingsStore.Intent.GetStreetObjectsForCoordinates -> {
            getStreetObjectsForCoordinates(intent.northwest, intent.southwest)
        }
        is ThingsStore.Intent.GetAllStreetObjects -> getAllStreetObjects()
    }

    private suspend fun getStreetObjectsForCoordinates(
        northwest: CoordinatePoint,
        southwest: CoordinatePoint
    ) {
        dispatch(ThingsStoreFactory.Message.SetLoading)
        when(val response = repository.getStreetObjectsForCoordinate(northwest, southwest)) {
            is ApiResponse.Success -> {
                dispatch(ThingsStoreFactory.Message.GotStreetObjects(response.body))
            }
            is ApiResponse.Error.HttpError -> {
                dispatch(ThingsStoreFactory.Message.SetHttpError(response))
            }
            is ApiResponse.Error.NetworkError -> {
                dispatch(ThingsStoreFactory.Message.SetNetworkError)
            }
            is ApiResponse.Error.SerializationError -> {
                dispatch(ThingsStoreFactory.Message.SetSerializationError)
            }
            else -> throw IllegalStateException("No such response type")
        }
    }

    private suspend fun getAllStreetObjects() {
        dispatch(ThingsStoreFactory.Message.SetLoading)
        when(val response = repository.getAllStreetObjects()) {
            is ApiResponse.Success -> {
                dispatch(ThingsStoreFactory.Message.GotStreetObjects(response.body))
            }
            is ApiResponse.Error.HttpError -> {
                dispatch(ThingsStoreFactory.Message.SetHttpError(response))
            }
            is ApiResponse.Error.NetworkError -> {
                dispatch(ThingsStoreFactory.Message.SetNetworkError)
            }
            is ApiResponse.Error.SerializationError -> {
                dispatch(ThingsStoreFactory.Message.SetSerializationError)
            }
            else -> throw IllegalStateException("No such response type")
        }
    }
}
