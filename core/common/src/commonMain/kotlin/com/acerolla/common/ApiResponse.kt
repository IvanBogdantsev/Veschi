package com.acerolla.common

sealed class ApiResponse<out T, out E> {

    data class Success<T>(val body: T): ApiResponse<T, Nothing>()

    sealed class Error<E>: ApiResponse<Nothing, E>() {

        data class HttpError<E>(val code: Int, val errorBody: E?): Error<E>()

        data object NetworkError: Error<Nothing>()

        data object SerializationError: Error<Nothing>()

        data object TimeoutError: Error<Nothing>()
    }
}