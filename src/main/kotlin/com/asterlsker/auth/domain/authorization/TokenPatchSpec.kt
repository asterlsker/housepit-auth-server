package com.asterlsker.auth.domain.authorization

data class TokenPatchSpec(
    val accessToken: String,
    val refreshToken: String
)