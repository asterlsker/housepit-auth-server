package com.asterlsker.auth.domain.authorization.token

import com.asterlsker.auth.domain.authentication.UserDetails

interface OAuthTokenDecoder {

    companion object {
        const val BEAN_NAME_SUFFIX = "OAuthTokenDecoder"
    }

    suspend fun decode(token: String): UserDetails
}