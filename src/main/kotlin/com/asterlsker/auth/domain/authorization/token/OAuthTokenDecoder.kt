package com.asterlsker.auth.domain.authorization.token

import com.asterlsker.auth.domain.authentication.UserDetails

interface OAuthTokenDecoder {
    suspend fun decode(token: String): UserDetails
}