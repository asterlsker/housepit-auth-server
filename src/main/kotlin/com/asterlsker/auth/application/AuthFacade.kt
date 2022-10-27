package com.asterlsker.auth.application

import com.asterlsker.auth.domain.authorization.AuthCommand
import com.asterlsker.auth.domain.authorization.JwtDecoder
import com.asterlsker.auth.domain.authorization.JwtProvider
import com.asterlsker.auth.domain.authorization.SignInType
import com.asterlsker.auth.domain.authorization.TokenIssueSpec
import com.asterlsker.auth.domain.member.MemberService
import org.springframework.stereotype.Component

@Component
class AuthFacade(
    private val memberService: MemberService,
    private val jwtDecoder: JwtDecoder,
    private val jwtProvider: JwtProvider
) {

    /**
     * 1. idToken -> email 등은 분리
     * 2. email 을 통해서 회원이 존재하는지 확인
     * 3. 토큰 발급
     */
    fun signIn(request: AuthCommand.SignInRequest): AuthCommand.SignInResponse {
        val email = jwtDecoder.decodeBase64(request.token) // TODO 추후에는 email 이 아닌 decode obj 일 수 있음
        memberService.validateEmail(email)
        val tokens = jwtProvider.issueTokens(request = TokenIssueSpec(
            payload = email,
            provider = request.provider
        ))
        return AuthCommand.SignInResponse(
            accessToken = tokens.accessToken,
            refreshToken = tokens.refreshToken,
            type = SignInType.NEW
        )
    }
}