package com.acerolla.add_thing_api

import com.acerolla.add_thing_api.models.CoordinatePointDto
import com.acerolla.add_thing_api.models.StreetObjectDto
import com.acerolla.add_thing_api.models.StreetObjectResponse
import com.acerolla.add_thing_api.models.StreetObjectUUIDDto
import com.acerolla.common.ApiResponse
import com.acerolla.common.ErrorResponse

interface ThingsNetworkService {

    suspend fun getStreetObjectsForCoordinate(northwest: CoordinatePointDto, southeast: CoordinatePointDto): ApiResponse<StreetObjectResponse, ErrorResponse>

    suspend fun getStreetObjectInfoByUUID(uuid: StreetObjectUUIDDto): ApiResponse<StreetObjectDto, ErrorResponse>

    suspend fun deleteStreetObjectByUUID(uuid: StreetObjectUUIDDto)

    // TODO: for tests
    suspend fun getAllStreetObjects(): ApiResponse<StreetObjectResponse, ErrorResponse>
}