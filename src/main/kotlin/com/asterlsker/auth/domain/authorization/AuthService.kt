package com.asterlsker.auth.domain.authorization

import com.asterlsker.auth.common.exception.domain.ExistMemberException
import com.asterlsker.auth.common.exception.domain.InvalidTokenException
import com.asterlsker.auth.common.exception.domain.NotExistMemberException
import com.asterlsker.auth.domain.authorization.token.JwtDecoder
import com.asterlsker.auth.domain.authorization.token.JwtProvider
import com.asterlsker.auth.domain.authorization.token.TokenIssueSpec
import com.asterlsker.auth.domain.member.MemberReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val jwtDecoder: JwtDecoder,
    private val jwtProvider: JwtProvider,
    private val memberReader: MemberReader,
) {

    @Transactional
    fun signIn(request: AuthCommand.SignInRequest): AuthCommand.SignInResponse {
        val email = jwtDecoder.decodeBase64(request.oAuthToken)
        if (!memberReader.existsByEmail(email)) throw NotExistMemberException()
        val tokens = jwtProvider.issueTokens(TokenIssueSpec(payload = email, provider = request.provider))

        return AuthCommand.SignInResponse(accessToken = tokens.accessToken, refreshToken = tokens.refreshToken)
    }

    @Transactional
    fun signOut(request: AuthCommand.SignOutRequest) {
        when (jwtProvider.validateToken(request.accessToken)) {
            true -> jwtProvider.releaseTokens(request.accessToken)
            false -> throw InvalidTokenException()
        }
    }

    @Transactional
    fun link(request: AuthCommand.LinkRequest) {
        // 연동하려는 이메일이 이미 연동되어있는 이메일인지 검증
        val email = jwtDecoder.decodeBase64(request.oAuthToken)
        if (memberReader.existsByEmail(email)) throw ExistMemberException()

        // 현재 로그인되어있는 email 로 회원 조회
        val payload = jwtProvider.getPayload(request.accessToken)
        val member = memberReader.findByEmail(payload)

        member.link(provider = request.provider, email = email)
    }
}