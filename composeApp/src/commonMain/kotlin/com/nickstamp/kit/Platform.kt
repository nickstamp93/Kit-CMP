package com.nickstamp.kit

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform