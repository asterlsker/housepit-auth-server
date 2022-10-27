package com.asterlsker.auth.domain.authorization

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)