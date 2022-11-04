package com.asterlsker.auth.interfaces

import com.asterlsker.auth.application.AuthFacade
import com.asterlsker.auth.interfaces.mapper.AuthMapper
import com.asterlsker.housepit.grpc.AuthServiceGrpcKt
import com.asterlsker.housepit.grpc.DecodeRequest
import com.asterlsker.housepit.grpc.DecodeResponse
import com.asterlsker.housepit.grpc.LinkRequest
import com.asterlsker.housepit.grpc.RefreshRequest
import com.asterlsker.housepit.grpc.RefreshResponse
import com.asterlsker.housepit.grpc.SignInRequest
import com.asterlsker.housepit.grpc.SignInResponse
import com.asterlsker.housepit.grpc.SignOutRequest
import com.google.protobuf.Empty
import net.devh.boot.grpc.server.service.GrpcService
import org.springframework.beans.factory.annotation.Autowired

@GrpcService
class AuthGrpService: AuthServiceGrpcKt.AuthServiceCoroutineImplBase() {

    @Autowired
    private lateinit var authFacade: AuthFacade

    override suspend fun signIn(request: SignInRequest): SignInResponse {
        val response = authFacade.signIn(AuthMapper.of(request))
        return AuthMapper.of(response)
    }

    override suspend fun signOut(request: SignOutRequest): Empty {
        authFacade.signOut(AuthMapper.of(request))
        return Empty.getDefaultInstance()
    }

    override suspend fun link(request: LinkRequest): Empty {
        authFacade.link(AuthMapper.of(request))
        return super.link(request)
    }

    override suspend fun decode(request: DecodeRequest): DecodeResponse {
        val response = authFacade.decode(AuthMapper.of(request))
        return AuthMapper.of(response)
    }

    override suspend fun refresh(request: RefreshRequest): RefreshResponse {
        authFacade.refresh(AuthMapper.of(request))
        return super.refresh(request)
    }
}