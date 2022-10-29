package com.asterlsker.auth.domain.authorization

import com.asterlsker.auth.common.exception.domain.ExistMemberException
import com.asterlsker.auth.common.exception.domain.InvalidTokenException
import com.asterlsker.auth.common.exception.domain.NotExistMemberException
import com.asterlsker.auth.domain.authorization.token.JwtDecoder
import com.asterlsker.auth.domain.authorization.token.JwtProvider
import com.asterlsker.auth.domain.authorization.token.TokenIssueSpec
import com.asterlsker.auth.domain.member.MemberReader
import com.asterlsker.auth.domain.member.MemberStore
import com.asterlsker.auth.domain.model.Email
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val jwtDecoder: JwtDecoder,
    private val jwtProvider: JwtProvider,
    private val memberReader: MemberReader,
    private val memberStore: MemberStore
) {

    @Transactional
    suspend fun signIn(request: AuthCommand.SignInRequest): AuthCommand.SignInResponse {
        val rawEmail = jwtDecoder.decodeBase64(request.oAuthToken)
        val validEmail = Email(rawEmail)
        if (!memberReader.existsByEmail(validEmail)) throw NotExistMemberException()
        val tokens = jwtProvider.issueTokens(TokenIssueSpec(payload = rawEmail, provider = request.provider))

        return AuthCommand.SignInResponse(accessToken = tokens.accessToken, refreshToken = tokens.refreshToken)
    }

    @Transactional
    suspend fun signOut(request: AuthCommand.SignOutRequest) {
        when (jwtProvider.validateToken(request.accessToken)) {
            true -> jwtProvider.releaseTokens(request.accessToken)
            false -> throw InvalidTokenException()
        }
    }

    @Transactional
    suspend fun link(request: AuthCommand.LinkRequest) {
        // 연동하려는 이메일이 이미 연동되어있는 이메일인지 검증
        val rawEmail = jwtDecoder.decodeBase64(request.oAuthToken)
        val validNewEmail = Email(rawEmail)
        if (memberReader.existsByEmail(validNewEmail)) throw ExistMemberException()

        // 현재 로그인되어있는 email 로 회원 조회
        val payload = jwtProvider.getPayload(request.accessToken)
        val member = memberReader.findByEmail(Email(payload))

        member.link(provider = request.provider, email = validNewEmail)
        memberStore.save(member)
    }
}