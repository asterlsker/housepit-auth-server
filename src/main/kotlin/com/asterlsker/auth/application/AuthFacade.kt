package com.asterlsker.auth.application

import com.asterlsker.auth.domain.authorization.AuthCommand
import com.asterlsker.auth.domain.authorization.AuthService
import com.asterlsker.auth.domain.authorization.JwtDecoder
import com.asterlsker.auth.domain.authorization.JwtProvider
import com.asterlsker.auth.domain.authorization.TokenIssueSpec
import com.asterlsker.auth.domain.member.MemberService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AuthFacade(
    private val authService: AuthService
) {

    @Transactional
    fun signIn(request: AuthCommand.SignInRequest): AuthCommand.SignInResponse {
        return authService.signIn(request)
    }

    @Transactional
    fun signOut(request: AuthCommand.SignOutRequest) {
        authService.signOut(request)
    }

    @Transactional
    fun link(request: AuthCommand.LinkRequest) {
        authService.link(request)
    }
}