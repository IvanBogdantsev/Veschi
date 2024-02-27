package com.acerolla.data

import com.acerolla.api.StreetObjectsRepository
import com.acerolla.api.ThingsNetworkService
import com.acerolla.api.models.CoordinatePoint
import com.acerolla.api.models.NewStreetObject
import com.acerolla.api.models.StreetObject
import com.acerolla.api.models.StreetObjectDto
import com.acerolla.api.models.StreetObjectResponse
import com.acerolla.api.models.StreetObjectUUID
import com.acerolla.api.models.StreetObjectsForCoordinate
import com.acerolla.common.ApiResponse
import com.acerolla.common.ErrorResponse
import com.acerolla.common.mappers.BaseApiResponseMapper
import com.acerolla.data.mappers.toDomainModel
import com.acerolla.data.mappers.toDto

class StreetObjectsRepositoryImpl(
    private val thingsNetworkService: ThingsNetworkService,
    private val streetObjectResponseDtoToDomainModelMapper: BaseApiResponseMapper<StreetObjectResponse, StreetObjectsForCoordinate>,
    private val streetObjectDtoToDomainMapper: BaseApiResponseMapper<StreetObjectDto, StreetObject>
): StreetObjectsRepository {

    override suspend fun getStreetObjectsForCoordinate(
        northwest: CoordinatePoint,
        southeast: CoordinatePoint
    ): ApiResponse<StreetObjectsForCoordinate, ErrorResponse> {
        // FIXME: Move logic to mapper
        return when(val from = thingsNetworkService.getStreetObjectsForCoordinate(northwest.toDto(), southeast.toDto())) {
            is ApiResponse.Success -> ApiResponse.Success(from.body.toDomainModel())
            is ApiResponse.Error.HttpError -> ApiResponse.Error.HttpError(from.code, from.errorBody)
            is ApiResponse.Error.NetworkError -> ApiResponse.Error.NetworkError
            is ApiResponse.Error.SerializationError -> ApiResponse.Error.SerializationError
            is ApiResponse.Error.TimeoutError -> ApiResponse.Error.TimeoutError
        }
//        return streetObjectResponseDtoToDomainModelMapper.map(
//            thingsNetworkService.getStreetObjectsForCoordinate(northwest.toDto(), southeast.toDto())
//        )
    }

    override suspend fun getStreetObjectInfoByUUID(uuid: StreetObjectUUID): ApiResponse<StreetObject, ErrorResponse> {
        return streetObjectDtoToDomainMapper.map(
            thingsNetworkService.getStreetObjectInfoByUUID(uuid.toDto())
        )
    }

    override suspend fun deleteStreetObjectByUUID(uuid: StreetObjectUUID) {
        thingsNetworkService.deleteStreetObjectByUUID(uuid.toDto())
    }

    override suspend fun addStreetObject(obj: NewStreetObject): ApiResponse<StreetObject, ErrorResponse> {
        return streetObjectDtoToDomainMapper.map(thingsNetworkService.addStreetObject(obj.toDto()))
    }
}