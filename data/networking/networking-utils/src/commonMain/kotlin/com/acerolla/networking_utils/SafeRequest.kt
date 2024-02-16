package com.acerolla.networking_utils

import com.acerolla.common.ApiResponse
import com.acerolla.common.ErrorResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.JsonConvertException
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException

suspend inline fun <reified T, reified E> HttpClient.safeRequest(
    block: HttpRequestBuilder.() -> Unit,
): ApiResponse<T, E> =
    try {
        val response = request { block() }
        if (response.status == HttpStatusCode.OK) {
            ApiResponse.Success(response.body())
        } else {
            android.util.Log.d("TAG1", "safeRequest: ${response.body<ErrorResponse>()}")
            ApiResponse.Error.HttpError(response.status.value, response.body())
        }
    } catch (e: ClientRequestException) {
        android.util.Log.d("TAG1", "safeRequest: $e")
        ApiResponse.Error.HttpError(e.response.status.value, e.errorBody())
    } catch (e: ServerResponseException) {
        android.util.Log.d("TAG1", "safeRequest: $e")
        ApiResponse.Error.HttpError(e.response.status.value, e.errorBody())
    } catch (e: IOException) {
        android.util.Log.d("TAG1", "safeRequest: $e")
        ApiResponse.Error.NetworkError
    } catch (e: HttpRequestTimeoutException) {
        android.util.Log.d("TAG1", "safeRequest: $e")
        ApiResponse.Error.TimeoutError
    } catch (e: SerializationException) {
        android.util.Log.d("TAG1", "safeRequest: $e")
        ApiResponse.Error.SerializationError
    } catch (e: JsonConvertException) {
        android.util.Log.d("TAG1", "safeRequest: $e")
        ApiResponse.Error.SerializationError
    } catch (e: Exception) {
        android.util.Log.d("TAG1", "safeRequest: $e")
        ApiResponse.Error.NetworkError
    }

suspend inline fun <reified E> ResponseException.errorBody(): E? =
    try {
        response.body()
    } catch (e: SerializationException) {
        null
    }