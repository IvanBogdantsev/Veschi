package com.acerolla.profile_domain_api

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform