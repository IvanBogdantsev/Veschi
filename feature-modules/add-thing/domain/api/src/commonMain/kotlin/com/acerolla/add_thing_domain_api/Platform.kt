package com.acerolla.add_thing_domain_api

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform