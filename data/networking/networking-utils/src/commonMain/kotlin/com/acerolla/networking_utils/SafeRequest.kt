package com.acerolla.networking_utils

import co.touchlab.kermit.Logger
import com.acerolla.common.ApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.request
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
            Logger.i("Successful safeRequest") {
                response.request.url.toString()
            }
            ApiResponse.Error.HttpError(response.status.value, response.body())
        }
    } catch (e: ClientRequestException) {
        Logger.e("ClientRequestException", e)
        ApiResponse.Error.HttpError(e.response.status.value, e.errorBody())
    } catch (e: ServerResponseException) {
        Logger.e("ServerResponseException", e)
        ApiResponse.Error.HttpError(e.response.status.value, e.errorBody())
    } catch (e: IOException) {
        Logger.e("IOException", e)
        ApiResponse.Error.NetworkError
    } catch (e: HttpRequestTimeoutException) {
        Logger.e("HttpRequestTimeoutException", e)
        ApiResponse.Error.TimeoutError
    } catch (e: SerializationException) {
        Logger.e("SerializationException", e)
        ApiResponse.Error.SerializationError
    } catch (e: JsonConvertException) {
        Logger.e("JsonConvertException", e)
        ApiResponse.Error.SerializationError
    } catch (e: Exception) {
        Logger.e("Exception", e)
        ApiResponse.Error.NetworkError
    }

suspend inline fun <reified E> ResponseException.errorBody(): E? =
    try {
        response.body()
    } catch (e: SerializationException) {
        Logger.e("SerializationException", e)
        null
    }