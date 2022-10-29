package com.asterlsker.auth.domain.authorization

import com.asterlsker.auth.domain.model.Provider

class AuthCommand {

    data class SignInRequest(
        val oAuthToken: String,
        val provider: Provider
    )

    data class SignInResponse(
        val accessToken: String,
        val refreshToken: String
    )

    data class SignOutRequest(
        val accessToken: String
    )

    data class LinkRequest(
        val accessToken: String,
        val oAuthToken: String,
        val provider: Provider
    )
}