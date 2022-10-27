package com.asterlsker.auth.domain.authorization

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