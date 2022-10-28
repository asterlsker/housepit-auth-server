package com.asterlsker.auth.interfaces.mapper

import com.asterlsker.auth.domain.authorization.AuthCommand
import com.asterlsker.auth.domain.model.Provider

class AuthMapper {

    companion object {
        fun of(request: Auth.SignInRequest): AuthCommand.SignInRequest {
            return AuthCommand.SignInRequest(
                token = request.idToken,
                provider = Provider.findByName(request.provider.name)
            )
        }

        fun of(response: AuthCommand.SignInResponse): Auth.SignInResponse {
            val prototype = Auth.SignInResponse.newBuilder().apply {
                status = Auth.SignInStatus.NEW
                accessToken = response.accessToken
                refreshToken = response.refreshToken
            }
            return prototype.build()
        }
    }
}