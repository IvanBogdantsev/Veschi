package com.acerolla.impl

import com.acerolla.api.ThingsNetworkService
import com.acerolla.api.models.CoordinatePointDto
import com.acerolla.api.models.GetThingsRequest
import com.acerolla.api.models.NewStreetObjectDto
import com.acerolla.api.models.StreetObjectDto
import com.acerolla.api.models.StreetObjectResponse
import com.acerolla.api.models.StreetObjectUUIDDto
import com.acerolla.common.ApiResponse
import com.acerolla.common.ErrorResponse
import com.acerolla.networking_utils.BaseNetworkHttpClientProvider
import com.acerolla.networking_utils.NetworkClientProvider
import com.acerolla.networking_utils.safeRequest
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpMethod

class ThingsNetworkServiceImpl(
    private val baseNetworkHttpClientProvider: NetworkClientProvider
): ThingsNetworkService {

    private val httpClient by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        baseNetworkHttpClientProvider.provideBaseHttpClient()
    }

    override suspend fun getStreetObjectsForCoordinate(
        northwest: CoordinatePointDto,
        southeast: CoordinatePointDto
    ): ApiResponse<StreetObjectResponse, ErrorResponse> {
        return httpClient.safeRequest {
            method = HttpMethod.Post
            setBody(
                GetThingsRequest(
                    northeast = northwest,
                    southwest = southeast
                )
            )
            url(STREET_OBJECTS_BY_COORDINATES)
        }
    }

    override suspend fun getStreetObjectInfoByUUID(uuid: StreetObjectUUIDDto): ApiResponse<StreetObjectDto, ErrorResponse> {
        return httpClient.safeRequest {
            method = HttpMethod.Get
            parameter(UUID_PARAMETER, uuid.uuid)
            url(STREET_OBJECT_BY_UUID)
        }
    }

    override suspend fun deleteStreetObjectByUUID(uuid: StreetObjectUUIDDto) {
        httpClient.safeRequest<Boolean, ErrorResponse> {
            method = HttpMethod.Delete
            parameter(UUID_PARAMETER, uuid.uuid)
            url(DELETE_STREET_OBJECT_BY_UUID)
        }
    }

    override suspend fun addStreetObject(obj: NewStreetObjectDto): ApiResponse<StreetObjectDto, ErrorResponse> {
        return httpClient.safeRequest {
            method = HttpMethod.Put
            setBody(NewStreetObjectDto)
            url(ADD_STREET_OBJECT)
        }
    }

    override suspend fun getAllStreetObjects(): ApiResponse<StreetObjectResponse, ErrorResponse> {
        return httpClient.safeRequest {
            method = HttpMethod.Get
            url(ALL)
        }
    }

    private companion object {

        const val ALL = "things/all"
        const val STREET_OBJECTS_BY_COORDINATES = "things/get_for_coordinates"
        const val STREET_OBJECT_BY_UUID = "things/street_object_info"
        const val DELETE_STREET_OBJECT_BY_UUID = "things/delete"
        const val ADD_STREET_OBJECT = "things/add"
        const val UUID_PARAMETER = "uuid"
    }
}