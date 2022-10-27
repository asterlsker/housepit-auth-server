package com.asterlsker.auth.interfaces.mapper

import com.asterlsker.auth.domain.authorization.AuthCommand
import com.asterlsker.auth.domain.authorization.Provider

class AuthMapper {

    companion object {
        fun of(request: Auth.SignInRequest): AuthCommand.SignInRequest {
            return AuthCommand.SignInRequest(
                token = request.idToken,
                provider = Provider.findByName(request.provider.name)
            )
        }
    }
}