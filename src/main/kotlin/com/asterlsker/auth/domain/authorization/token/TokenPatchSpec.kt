package com.asterlsker.auth.domain.authorization.token

data class TokenPatchSpec(
    val accessToken: String,
    val refreshToken: String
)