package com.asterlsker.auth.interfaces.mapper

import com.asterlsker.auth.domain.authorization.AuthCommand
import com.asterlsker.auth.domain.model.Provider

class AuthMapper {

    companion object {
        fun of(request: Auth.SignInRequest): AuthCommand.SignInRequest {
            return AuthCommand.SignInRequest(
                oAuthToken = request.idToken,
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

        fun of(request: Auth.SignOutRequest): AuthCommand.SignOutRequest {
            return AuthCommand.SignOutRequest(request.accessToken)
        }

        fun of(request: Auth.LinkRequest): AuthCommand.LinkRequest {
            return AuthCommand.LinkRequest(
                oAuthToken = request.idToken,
                accessToken = request.accessToken,
                provider = Provider.findByName(request.provider.name)
            )
        }

        fun of(request: Auth.DecodeRequest): AuthCommand.DecodeRequest {
            return AuthCommand.DecodeRequest(request.accessToken)
        }

        fun of(response: AuthCommand.DecodeResponse): Auth.DecodeResponse {
            return Auth.DecodeResponse.newBuilder().apply {
                memberId = response.memberId
            }.build()
        }

        fun of(request: Auth.RefreshRequest): AuthCommand.RefreshRequest {
            return AuthCommand.RefreshRequest(request.refreshToken)
        }
    }
}