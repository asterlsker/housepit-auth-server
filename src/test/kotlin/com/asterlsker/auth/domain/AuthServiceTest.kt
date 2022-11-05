package com.asterlsker.auth.domain

import com.asterlsker.auth.domain.authorization.AuthService
import com.asterlsker.auth.domain.authorization.token.JwtDecoder
import com.asterlsker.auth.domain.authorization.token.JwtProvider
import com.asterlsker.auth.domain.authorization.token.TokenIssueSpec
import com.asterlsker.auth.domain.member.MemberReader
import com.asterlsker.auth.domain.member.MemberStore
import com.asterlsker.auth.domain.model.Email
import com.asterlsker.auth.infrastructure.member.repository.MemberReaderImpl
import com.asterlsker.auth.infrastructure.member.repository.MemberSocialLoginRepository
import com.asterlsker.auth.infrastructure.member.repository.MemberStoreImpl
import com.asterlsker.auth.support.MemberDummy.*
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.test.TestCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.mockito.ArgumentMatchers.any
import org.junit.jupiter.api.extension.ExtendWith
import org.assertj.core.api.Assertions.assertThat

// MemberReaderImpl(mockk(relaxed = true), mockk(relaxed = true))
@ExtendWith(MockKExtension::class)
internal class AuthServiceTest: DescribeSpec() {

    private lateinit var authService: AuthService
    @MockK lateinit var jwtDecoder: JwtDecoder
    @MockK lateinit var jwtProvider: JwtProvider
    private lateinit var memberReader: MemberReader
    private lateinit var memberStore: MemberStore

    companion object {
        val googleUser = GoogleUser()
    }

    override fun beforeEach(testCase: TestCase) {
        MockKAnnotations.init(this, relaxUnitFun = true)
        memberReader = spyk(MemberReaderImpl(mockk(), spyk()))
        memberStore = spyk(MemberStoreImpl(spyk()))
        authService = AuthService(this.jwtDecoder, this.jwtProvider, memberReader, memberStore)

        every { jwtDecoder.decodeBase64(googleUser.oAuthToken, googleUser.provider) } returns googleUser.email
        every { jwtProvider.issueTokens(TokenIssueSpec(googleUser.email, googleUser.provider)) } returns googleUser.tokenResponse()
    }

    init {
        describe("signIn") {
            it("If exists email then return tokens") {
                every { memberReader.existsByEmail(Email(googleUser.email)) } returns true

                val result = authService.signIn(googleUser.signInRequest())

                assertThat(result.accessToken).isNotBlank
                assertThat(result.refreshToken).isNotBlank
            }

            it("If not exists email then register user and return tokens") {
                every { memberReader.existsByEmail(Email(googleUser.email)) } returns false

                val result = authService.signIn(googleUser.signInRequest())

                assertThat(result.accessToken).isNotBlank
                assertThat(result.refreshToken).isNotBlank
                verify(exactly = 1) { memberStore.save(any()) }
                assertThat(memberReader.findByEmail(Email(googleUser.email))).isNotNull
            }
        }
    }
}