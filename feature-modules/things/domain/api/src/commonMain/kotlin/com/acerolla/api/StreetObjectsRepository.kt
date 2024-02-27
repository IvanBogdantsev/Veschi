package com.acerolla.api

import com.acerolla.api.models.CoordinatePoint
import com.acerolla.api.models.NewStreetObject
import com.acerolla.api.models.StreetObject
import com.acerolla.api.models.StreetObjectUUID
import com.acerolla.api.models.StreetObjectsForCoordinate
import com.acerolla.common.ApiResponse
import com.acerolla.common.ErrorResponse

interface StreetObjectsRepository {

    suspend fun getStreetObjectsForCoordinate(northwest: CoordinatePoint, southeast: CoordinatePoint): ApiResponse<StreetObjectsForCoordinate, ErrorResponse>

    suspend fun getStreetObjectInfoByUUID(uuid: StreetObjectUUID): ApiResponse<StreetObject, ErrorResponse>

    suspend fun deleteStreetObjectByUUID(uuid: StreetObjectUUID)

    suspend fun addStreetObject(obj: NewStreetObject): ApiResponse<StreetObject, ErrorResponse>

    // TODO: for tests
    suspend fun getAllStreetObjects(): ApiResponse<StreetObjectsForCoordinate, ErrorResponse>
}