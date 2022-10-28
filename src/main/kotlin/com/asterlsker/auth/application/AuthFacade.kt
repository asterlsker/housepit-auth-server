package com.asterlsker.auth.application

import com.asterlsker.auth.domain.authorization.AuthCommand
import com.asterlsker.auth.domain.authorization.AuthService
import com.asterlsker.auth.domain.authorization.JwtDecoder
import com.asterlsker.auth.domain.authorization.JwtProvider
import com.asterlsker.auth.domain.authorization.TokenIssueSpec
import com.asterlsker.auth.domain.member.MemberService
import org.springframework.stereotype.Component

@Component
class AuthFacade(
    private val authService: AuthService
) {

    fun signIn(request: AuthCommand.SignInRequest): AuthCommand.SignInResponse {
        return authService.signIn(request)
    }
}