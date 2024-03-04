package com.acerolla.add_thing_domain_api.model

sealed class AddStreetObjectResult {

    data object Success: AddStreetObjectResult()

    data object Failed: AddStreetObjectResult()
}