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

    data class DecodeRequest(
        val accessToken: String
    )

    data class DecodeResponse(
        val memberId: String
    )

    data class RefreshRequest(
        val refreshToken: String
    )

    data class RefreshResponse(
        val accessToken: String,
        val refreshToken: String
    )

    data class LookupMemberRequest(
        val memberUuid: String,
        val accessToken: String
    )

    data class LookupMemberResponse(
        val memberUuid: String,
        val userName: String
    )

    data class UpdateMemberRequest(
        val accessToken: String,
        val userName: String
    )
}