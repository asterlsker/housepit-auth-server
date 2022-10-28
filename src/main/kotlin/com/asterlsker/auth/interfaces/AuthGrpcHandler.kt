package com.asterlsker.auth.interfaces

import AuthServiceGrpcKt
import com.asterlsker.auth.application.AuthFacade
import com.asterlsker.auth.interfaces.mapper.AuthMapper
import com.google.protobuf.Empty
import net.devh.boot.grpc.server.service.GrpcService


@GrpcService
class AuthGrpcHandler(
    private val authFacade: AuthFacade
): AuthServiceGrpcKt.AuthServiceCoroutineImplBase() {

    override suspend fun signIn(request: Auth.SignInRequest): Auth.SignInResponse {
        val response = authFacade.signIn(AuthMapper.of(request))
        return AuthMapper.of(response)
    }

    override suspend fun signOut(request: Auth.SignOutRequest): Empty {
        authFacade.signOut(AuthMapper.of(request))
        return Empty.getDefaultInstance()
    }

    override suspend fun link(request: Auth.LinkRequest): Empty {
        return super.link(request)
    }

    override suspend fun decode(request: Auth.DecodeRequest): Auth.DecodeResponse {
        return super.decode(request)
    }

    override suspend fun refresh(request: Auth.RefreshRequest): Auth.RefreshResponse {
        return super.refresh(request)
    }
}