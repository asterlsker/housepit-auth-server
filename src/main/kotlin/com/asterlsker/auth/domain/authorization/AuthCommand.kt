package com.asterlsker.auth.domain.authorization

import com.asterlsker.auth.domain.model.Provider

class AuthCommand {

    data class SignInRequest(
        val token: String,
        val provider: Provider
    )

    data class SignInResponse(
        val accessToken: String,
        val refreshToken: String
    )
}