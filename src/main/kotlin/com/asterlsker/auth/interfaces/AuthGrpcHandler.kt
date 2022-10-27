package com.asterlsker.auth.interfaces

import AuthServiceGrpcKt
import com.asterlsker.auth.application.AuthFacade
import com.asterlsker.auth.interfaces.mapper.AuthMapper
import net.devh.boot.grpc.server.service.GrpcService


@GrpcService
class AuthGrpcHandler(
    private val authFacade: AuthFacade
): AuthServiceGrpcKt.AuthServiceCoroutineImplBase() {

    override suspend fun signIn(request: Auth.SignInRequest): Auth.SignInResponse {
        authFacade.signIn(AuthMapper.of(request))
        return super.signIn(request)
    }
}