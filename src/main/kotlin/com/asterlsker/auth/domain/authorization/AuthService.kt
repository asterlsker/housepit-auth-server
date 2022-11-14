package com.asterlsker.auth.domain.authorization

import com.asterlsker.auth.common.exception.domain.ExistMemberException
import com.asterlsker.auth.common.exception.domain.InvalidTokenException
import com.asterlsker.auth.common.exception.domain.NotExistMemberException
import com.asterlsker.auth.domain.authorization.token.JwtProvider
import com.asterlsker.auth.domain.authorization.token.OAuthTokenDecoder
import com.asterlsker.auth.domain.authorization.token.TokenIssueSpec
import com.asterlsker.auth.domain.factory.OAuthTokenDecoderFactory
import com.asterlsker.auth.domain.member.Member
import com.asterlsker.auth.domain.member.MemberReader
import com.asterlsker.auth.domain.member.MemberSocialLogin
import com.asterlsker.auth.domain.member.MemberStore
import com.asterlsker.auth.domain.model.Email
import com.asterlsker.auth.domain.model.Provider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val tokenDecoders: Map<String, OAuthTokenDecoder>,
    private val jwtProvider: JwtProvider,
    private val memberReader: MemberReader,
    private val memberStore: MemberStore
) {

    @Transactional
    suspend fun signIn(request: AuthCommand.SignInRequest): AuthCommand.SignInResponse {
        val tokenDecoder = OAuthTokenDecoderFactory.of(tokenDecoders, request.provider)
        val userDetails = tokenDecoder.decode(token = request.oAuthToken)
        validEmailAndRegisterMember(email = userDetails.email, provider = request.provider)

        // TODO TokenIssueSpec 에 member-id 도 넣어야 함
        val tokens = jwtProvider.issueTokens(TokenIssueSpec(email = userDetails.email, provider = request.provider))

        // TODO TokenResponse 에 권한 넣기
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
        val newEmail = checkIsAlreadyLinked(request)
        val member = getRegisteredMember(request.accessToken)
        member.link(provider = request.provider, email = Email(newEmail))
        memberStore.save(member)
    }

    @Transactional
    suspend fun decode(request: AuthCommand.DecodeRequest): AuthCommand.DecodeResponse {
        val payload = jwtProvider.getPayload(request.accessToken)
        val member = memberReader.findByEmail(payload.email) ?: throw NotExistMemberException()
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

    private suspend fun validEmailAndRegisterMember(email: String, provider: Provider) {
        if (!memberReader.existsByEmail(email)) {
            val validEmail = Email(email)
            val member = Member.new(validEmail)
            member.register(MemberSocialLogin(provider = provider, email = validEmail))
            memberStore.save(member)
        }
    }

    /**
     * 연동하려는 이메일이 이미 등록 되어있는지 검증하고 새로 등록하려는 이메일을 반환
     */
    private suspend fun checkIsAlreadyLinked(request: AuthCommand.LinkRequest): String {
        val tokenDecoder = OAuthTokenDecoderFactory.of(tokenDecoders, request.provider)
        val userDetails = tokenDecoder.decode(request.oAuthToken)
        if (memberReader.existsByEmail(userDetails.email)) throw ExistMemberException()
        return userDetails.email
    }

    private suspend fun getRegisteredMember(accessToken: String): Member {
        val payload = jwtProvider.getPayload(accessToken)
        return memberReader.findByEmail(payload.email) ?: throw NotExistMemberException()
    }
}