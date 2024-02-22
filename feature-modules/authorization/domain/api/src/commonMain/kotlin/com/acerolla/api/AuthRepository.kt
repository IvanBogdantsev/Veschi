package com.acerolla.api

import com.acerolla.api.models.SignInModel
import com.acerolla.api.models.SignUpModel
import com.acerolla.common.ApiResponse
import com.acerolla.common.ErrorResponse
import com.acerolla.common.TokenResponse

interface AuthRepository {

    suspend fun signUp(signUpModel: SignUpModel): ApiResponse<TokenResponse, ErrorResponse>

    suspend fun signIn(signInModel: SignInModel): ApiResponse<TokenResponse, ErrorResponse>

    suspend fun saveTokens(tokens: TokenResponse)

    suspend fun markUserAuthorized()
}