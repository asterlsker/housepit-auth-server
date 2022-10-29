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
}