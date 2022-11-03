package com.asterlsker.auth.interfaces

import com.asterlsker.auth.application.AuthFacade
import com.asterlsker.auth.interfaces.mapper.AuthMapper
import com.google.protobuf.Empty
import net.devh.boot.grpc.server.service.GrpcService
import org.springframework.beans.factory.annotation.Autowired

@GrpcService
class AuthGrpService: AuthServiceGrpcKt.AuthServiceCoroutineImplBase() {

    @Autowired
    private lateinit var authFacade: AuthFacade

    override suspend fun signIn(request: Auth.SignInRequest): Auth.SignInResponse {
        val response = authFacade.signIn(AuthMapper.of(request))
        return AuthMapper.of(response)
    }

    override suspend fun signOut(request: Auth.SignOutRequest): Empty {
        authFacade.signOut(AuthMapper.of(request))
        return Empty.getDefaultInstance()
    }

    override suspend fun link(request: Auth.LinkRequest): Empty {
        authFacade.link(AuthMapper.of(request))
        return super.link(request)
    }

    override suspend fun decode(request: Auth.DecodeRequest): Auth.DecodeResponse {
        val response = authFacade.decode(AuthMapper.of(request))
        return AuthMapper.of(response)
    }

    override suspend fun refresh(request: Auth.RefreshRequest): Auth.RefreshResponse {
        authFacade.refresh(AuthMapper.of(request))
        return super.refresh(request)
    }
}