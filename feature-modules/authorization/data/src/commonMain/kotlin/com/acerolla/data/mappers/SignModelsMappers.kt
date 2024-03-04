package com.acerolla.data.mappers

import com.acerolla.add_thing_api.models.SignInDto
import com.acerolla.add_thing_api.models.SignInModel
import com.acerolla.add_thing_api.models.SignUpDto
import com.acerolla.add_thing_api.models.SignUpModel

fun SignUpModel.toDto() = SignUpDto(email, username, password)

fun SignInModel.toDto() = SignInDto(email, password)