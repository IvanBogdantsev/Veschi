package com.acerolla.impl

import com.acerolla.add_thing_api.AddThingNetworkService
import com.acerolla.add_thing_api.models.NewStreetObjectDto
import com.acerolla.add_thing_api.models.StreetObjectDto
import com.acerolla.common.ApiResponse
import com.acerolla.common.ErrorResponse
import com.acerolla.networking_utils.NetworkClientProvider
import com.acerolla.networking_utils.safeRequest
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpMethod

class AddThingNetworkServiceImpl(
    private val networkClientProvider: NetworkClientProvider
): AddThingNetworkService {

    private val httpClient = networkClientProvider.provideBaseHttpClient()

    override suspend fun addNewObject(streetObject: NewStreetObjectDto): ApiResponse<StreetObjectDto, ErrorResponse> {
        return httpClient.safeRequest {
            method = HttpMethod.Put
            setBody(streetObject)
            url(ADD_THING)
        }
    }

    private companion object {

        const val ADD_THING = "things/add"
    }
}