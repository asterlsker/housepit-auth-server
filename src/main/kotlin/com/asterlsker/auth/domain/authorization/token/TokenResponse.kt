package com.asterlsker.auth.domain.authorization.token


data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)