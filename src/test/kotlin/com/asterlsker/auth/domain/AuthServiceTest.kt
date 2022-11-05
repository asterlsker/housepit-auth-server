package com.asterlsker.auth.domain

import com.asterlsker.auth.domain.authorization.AuthService
import com.asterlsker.auth.domain.authorization.token.JwtDecoder
import com.asterlsker.auth.domain.authorization.token.JwtProvider
import com.asterlsker.auth.domain.authorization.token.TokenIssueSpec
import com.asterlsker.auth.domain.member.MemberReader
import com.asterlsker.auth.domain.member.MemberStore
import com.asterlsker.auth.domain.model.Email
import com.asterlsker.auth.support.MemberDummy.*
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.test.TestCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.extension.ExtendWith
import org.assertj.core.api.Assertions.assertThat

@ExtendWith(MockKExtension::class)
internal class AuthServiceTest: DescribeSpec() {

    @InjectMockKs lateinit var authService: AuthService
    @MockK lateinit var jwtDecoder: JwtDecoder
    @MockK lateinit var jwtProvider: JwtProvider
    @MockK lateinit var memberReader: MemberReader
    @MockK lateinit var memberStore: MemberStore

    companion object {
        val googleUser = GoogleUser()
    }

    init {
        describe("signIn") {
            it("Success signIn then return tokens") {
                val result = authService.signIn(googleUser.signInRequest())
                assertThat(result.accessToken).isNotBlank
                assertThat(result.refreshToken).isNotBlank
            }
        }
    }

    override fun beforeEach(testCase: TestCase) {
        MockKAnnotations.init(this, relaxUnitFun = true)

        every { jwtDecoder.decodeBase64(googleUser.oAuthToken) } returns googleUser.email
        every { memberReader.existsByEmail(Email(googleUser.email)) } returns true
        every { jwtProvider.issueTokens(TokenIssueSpec(googleUser.email, googleUser.provider)) } returns googleUser.tokenResponse()
    }
}