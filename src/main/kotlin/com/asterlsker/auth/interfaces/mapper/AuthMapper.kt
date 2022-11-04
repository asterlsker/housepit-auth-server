package com.asterlsker.auth.interfaces.mapper

import com.asterlsker.auth.domain.authorization.AuthCommand
import com.asterlsker.auth.domain.model.Provider
import com.asterlsker.housepit.grpc.DecodeRequest
import com.asterlsker.housepit.grpc.DecodeResponse
import com.asterlsker.housepit.grpc.LinkRequest
import com.asterlsker.housepit.grpc.RefreshRequest
import com.asterlsker.housepit.grpc.SignInRequest
import com.asterlsker.housepit.grpc.SignInResponse
import com.asterlsker.housepit.grpc.SignInStatus
import com.asterlsker.housepit.grpc.SignOutRequest

class AuthMapper {

    companion object {
        fun of(request: SignInRequest): AuthCommand.SignInRequest {
            return AuthCommand.SignInRequest(
                oAuthToken = request.idToken,
                provider = Provider.findByName(request.provider.name)
            )
        }

        fun of(response: AuthCommand.SignInResponse): SignInResponse {
            val prototype = SignInResponse.newBuilder().apply {
                status = SignInStatus.NEW
                accessToken = response.accessToken
                refreshToken = response.refreshToken
            }
            return prototype.build()
        }

        fun of(request: SignOutRequest): AuthCommand.SignOutRequest {
            return AuthCommand.SignOutRequest(request.accessToken)
        }

        fun of(request: LinkRequest): AuthCommand.LinkRequest {
            return AuthCommand.LinkRequest(
                oAuthToken = request.idToken,
                accessToken = request.accessToken,
                provider = Provider.findByName(request.provider.name)
            )
        }

        fun of(request: DecodeRequest): AuthCommand.DecodeRequest {
            return AuthCommand.DecodeRequest(request.accessToken)
        }

        fun of(response: AuthCommand.DecodeResponse): DecodeResponse {
            return DecodeResponse.newBuilder().apply {
                memberId = response.memberId
            }.build()
        }

        fun of(request: RefreshRequest): AuthCommand.RefreshRequest {
            return AuthCommand.RefreshRequest(request.refreshToken)
        }
    }
}