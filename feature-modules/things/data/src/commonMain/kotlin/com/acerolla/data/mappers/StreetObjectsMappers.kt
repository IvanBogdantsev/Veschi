package com.acerolla.data.mappers

import com.acerolla.api.models.Category
import com.acerolla.api.models.Condition
import com.acerolla.api.models.CoordinatePoint
import com.acerolla.api.models.CoordinatePointDto
import com.acerolla.api.models.Geometry
import com.acerolla.api.models.GeometryDto
import com.acerolla.api.models.NewStreetObject
import com.acerolla.api.models.NewStreetObjectDto
import com.acerolla.api.models.StreetObject
import com.acerolla.api.models.StreetObjectDto
import com.acerolla.api.models.StreetObjectResponse
import com.acerolla.api.models.StreetObjectUUID
import com.acerolla.api.models.StreetObjectUUIDDto
import com.acerolla.api.models.StreetObjectsForCoordinate
import com.acerolla.common.ApiResponse
import com.acerolla.common.mappers.BaseApiResponseMapper

class StreetObjectResponseDtoToDomainModelMapper: BaseApiResponseMapper<StreetObjectResponse, StreetObjectsForCoordinate> {

    override fun <E> map(from: ApiResponse<StreetObjectResponse, E>): ApiResponse<StreetObjectsForCoordinate, E> {
        return when(from) {
            is ApiResponse.Success -> ApiResponse.Success(from.body.toDomainModel())
            is ApiResponse.Error.HttpError -> ApiResponse.Error.HttpError(from.code, from.errorBody)
            is ApiResponse.Error.NetworkError -> ApiResponse.Error.NetworkError
            is ApiResponse.Error.SerializationError -> ApiResponse.Error.SerializationError
            is ApiResponse.Error.TimeoutError -> ApiResponse.Error.TimeoutError
        }
    }
}

class StreetObjectDtoToDomainModelMapper: BaseApiResponseMapper<StreetObjectDto, StreetObject> {

    override fun <E> map(from: ApiResponse<StreetObjectDto, E>): ApiResponse<StreetObject, E> {
        return when(from) {
            is ApiResponse.Success -> ApiResponse.Success(from.body.toDomainModel())
            is ApiResponse.Error.HttpError -> ApiResponse.Error.HttpError(from.code, from.errorBody)
            is ApiResponse.Error.NetworkError -> ApiResponse.Error.NetworkError
            is ApiResponse.Error.SerializationError -> ApiResponse.Error.SerializationError
            is ApiResponse.Error.TimeoutError -> ApiResponse.Error.TimeoutError
        }
    }
}

fun StreetObjectResponse.toDomainModel(): StreetObjectsForCoordinate {
    return StreetObjectsForCoordinate(
        objects = this.streetObjects.map { it.toDomainModel() }
    )
}

fun StreetObjectDto.toDomainModel(): StreetObject {
    return StreetObject(
        objectId = objectId,
        userId = userId,
        claimedUsersId = claimedUsersId,
        likedUsersId = likedUsersId,
        name = name,
        description = description,
        vicinity = vicinity,
        geometry = geometry.toDomainModel(),
        ownedByUser = ownedByUser,
        category = category.toCategory(),
        keywords = keywords,
        images = images,
        thumbnailImage = thumbnailImage,
        conditionIcon = conditionIcon,
        timestamp = timestamp,
        condition = condition.toCondition()
    )
}

fun String?.toCondition(): Condition? {
    return when (this) {
        "good" -> Condition.GOOD
        null -> null
        else -> Condition.POOR
    }
}

fun String?.toCategory(): Category? {
    return when (this) {
        "good" -> Category.BOOKS
        null -> null
        else -> Category.ELECTRONICS
    }
}

fun GeometryDto.toDomainModel(): Geometry {
    return Geometry(
        latitude = latitude,
        longitude = longitude,
        accuracy = accuracy
    )
}

fun CoordinatePoint.toDto(): CoordinatePointDto {
    return CoordinatePointDto(
        latitude = latitude,
        longitude = longitude
    )
}

fun StreetObjectUUID.toDto(): StreetObjectUUIDDto {
    return StreetObjectUUIDDto(
        uuid = uuid
    )
}

fun NewStreetObject.toDto(): NewStreetObjectDto {
    return NewStreetObjectDto(
        userId,
        name,
        description,
        vicinity,
        geometry.toDto(),
        ownedByUser,
        category?.name,
        images,
        thumbnailImage,
        conditionIcon,
        timestamp,
        condition?.name
    )
}

fun Geometry.toDto(): GeometryDto {
    return GeometryDto(
        latitude = latitude,
        longitude = longitude,
        accuracy = accuracy
    )
}