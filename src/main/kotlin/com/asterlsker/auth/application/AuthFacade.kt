package com.asterlsker.auth.application

import com.asterlsker.auth.domain.authorization.AuthCommand
import com.asterlsker.auth.domain.authorization.AuthService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AuthFacade(
    private val authService: AuthService
) {

    @Transactional
    suspend fun signIn(request: AuthCommand.SignInRequest): AuthCommand.SignInResponse {
        return authService.signIn(request)
    }

    @Transactional
    suspend fun signOut(request: AuthCommand.SignOutRequest) {
        authService.signOut(request)
    }

    @Transactional
    suspend fun link(request: AuthCommand.LinkRequest) {
        authService.link(request)
    }

    @Transactional
    suspend fun decode(request: AuthCommand.DecodeRequest): AuthCommand.DecodeResponse {
        return authService.decode(request)
    }

    @Transactional
    suspend fun refresh(request: AuthCommand.RefreshRequest): AuthCommand.RefreshResponse {
        return authService.refresh(request)
    }

    @Transactional
    suspend fun lookupMember(request: AuthCommand.LookupMemberRequest): AuthCommand.LookupMemberResponse {
        return authService.lookupMember(request)
    }

    @Transactional
    suspend fun updateMember(request: AuthCommand.UpdateMemberRequest) {
        return authService.updateMember(request)
    }

    @Transactional
    suspend fun saveCertification(request: AuthCommand.SaveCertificationRequest) {
        authService.saveCertification(request)
    }
}