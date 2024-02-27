package com.acerolla.api

import com.acerolla.api.models.CoordinatePointDto
import com.acerolla.api.models.NewStreetObjectDto
import com.acerolla.api.models.StreetObjectDto
import com.acerolla.api.models.StreetObjectResponse
import com.acerolla.api.models.StreetObjectUUIDDto
import com.acerolla.common.ApiResponse
import com.acerolla.common.ErrorResponse

interface ThingsNetworkService {

    suspend fun getStreetObjectsForCoordinate(northwest: CoordinatePointDto, southeast: CoordinatePointDto): ApiResponse<StreetObjectResponse, ErrorResponse>

    suspend fun getStreetObjectInfoByUUID(uuid: StreetObjectUUIDDto): ApiResponse<StreetObjectDto, ErrorResponse>

    suspend fun deleteStreetObjectByUUID(uuid: StreetObjectUUIDDto)

    suspend fun addStreetObject(obj: NewStreetObjectDto): ApiResponse<StreetObjectDto, ErrorResponse>

    // TODO: for tests
    suspend fun getAllStreetObjects(): ApiResponse<StreetObjectResponse, ErrorResponse>
}