package com.asterlsker.auth.domain.authorization

import com.asterlsker.auth.common.exception.domain.ExistMemberException
import com.asterlsker.auth.common.exception.domain.InvalidTokenException
import com.asterlsker.auth.common.exception.domain.NotExistMemberException
import com.asterlsker.auth.domain.authorization.token.JwtDecoder
import com.asterlsker.auth.domain.authorization.token.JwtProvider
import com.asterlsker.auth.domain.authorization.token.TokenIssueSpec
import com.asterlsker.auth.domain.member.Member
import com.asterlsker.auth.domain.member.MemberReader
import com.asterlsker.auth.domain.member.MemberSocialLogin
import com.asterlsker.auth.domain.member.MemberStore
import com.asterlsker.auth.domain.model.Email
import com.asterlsker.auth.domain.model.Phone
import com.asterlsker.auth.domain.model.Provider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val jwtDecoder: JwtDecoder,
    private val jwtProvider: JwtProvider,
    private val memberReader: MemberReader,
    private val memberStore: MemberStore
) {

    // TODO oAuthToken decode 결과가 핸드폰, 이메일과 이름이 나오는지 확인하고 리팩토링 필요
    @Transactional
    suspend fun signIn(request: AuthCommand.SignInRequest): AuthCommand.SignInResponse {
        val email = jwtDecoder.decodeBase64(token = request.oAuthToken, provider = request.provider)
        validEmailAndRegisterMember(email = Email(email), provider = request.provider)
        val tokens = jwtProvider.issueTokens(TokenIssueSpec(email = email, provider = request.provider))

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
        val email = jwtDecoder.decodeBase64(request.oAuthToken, request.provider)
        val validEmail = Email(email)
        if (memberReader.existsByEmail(validEmail)) throw ExistMemberException()

        // 현재 로그인되어있는 email 로 회원 조회
        val payload = jwtProvider.getPayload(request.accessToken)
        val member = memberReader.findByEmail(Email(payload.email)) ?: throw NotExistMemberException()

        member.link(provider = request.provider, email = validEmail)
        memberStore.save(member)
    }

    @Transactional
    suspend fun decode(request: AuthCommand.DecodeRequest): AuthCommand.DecodeResponse {
        val payload = jwtProvider.getPayload(request.accessToken)
        val member = memberReader.findByEmail(Email(payload.email)) ?: throw NotExistMemberException()
        return member.id!!.let { AuthCommand.DecodeResponse(it) }
    }

    @Transactional
    suspend fun refresh(request: AuthCommand.RefreshRequest): AuthCommand.RefreshResponse {
        val response = jwtProvider.refreshTokens(request.refreshToken)
        return AuthCommand.RefreshResponse(
            accessToken = response.accessToken,
            refreshToken = response.refreshToken
        )
    }

    // TODO 추후에는 파라미터로 디코드 결과를 담은 객체를 받으면 됨
    private suspend fun validEmailAndRegisterMember(email: Email, provider: Provider) {
        if (!memberReader.existsByEmail(email)) {
            val member = Member(userName = "jungHo", phone = Phone("01089241810"))
            val memberSocialLogin = MemberSocialLogin(provider = provider, email = email)
            member.register(memberSocialLogin)
            memberStore.save(member)
        }
    }
}