//package com.asterlsker.auth.domain
//
//import com.asterlsker.auth.common.exception.domain.InvalidTokenException
//import com.asterlsker.auth.domain.authentication.UserDetails
//import com.asterlsker.auth.domain.authorization.AuthService
//import com.asterlsker.auth.domain.authorization.token.JwtProvider
//import com.asterlsker.auth.domain.authorization.token.OAuthTokenDecoder
//import com.asterlsker.auth.domain.authorization.token.TokenIssueSpec
//import com.asterlsker.auth.domain.factory.OAuthTokenDecoderFactory
//import com.asterlsker.auth.domain.member.MemberReader
//import com.asterlsker.auth.domain.member.MemberStore
//import com.asterlsker.auth.domain.model.Provider
//import com.asterlsker.auth.infrastructure.authorization.GoogleOAuthTokenDecoder
//import com.asterlsker.auth.support.MemberDummy.GoogleUser
//import io.kotest.core.spec.style.DescribeSpec
//import io.kotest.core.test.TestCase
//import io.mockk.MockKAnnotations
//import io.mockk.coEvery
//import io.mockk.coVerify
//import io.mockk.every
//import io.mockk.impl.annotations.InjectMockKs
//import io.mockk.impl.annotations.MockK
//import io.mockk.junit5.MockKExtension
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.jupiter.api.assertThrows
//import org.junit.jupiter.api.extension.ExtendWith
//
//@ExtendWith(MockKExtension::class)
//internal class AuthServiceTest: DescribeSpec() {
//
//    @InjectMockKs lateinit var authService: AuthService
//    @MockK lateinit var jwtDecoder: GoogleOAuthTokenDecoder
//    @MockK lateinit var jwtProvider: JwtProvider
//    @MockK lateinit var memberReader: MemberReader
//    @MockK lateinit var memberStore: MemberStore
//
//    companion object {
//        val googleUser = GoogleUser()
//    }
//
//    override fun beforeEach(testCase: TestCase) {
//        MockKAnnotations.init(this, relaxUnitFun = true)
//
//        coEvery { jwtDecoder.decode(googleUser.oAuthToken) } returns UserDetails(googleUser.email)
//        every { jwtProvider.issueTokens(TokenIssueSpec(googleUser.email, googleUser.provider)) } returns googleUser.tokenResponse()
//    }
//
//    init {
//        describe("signIn") {
//            it("If exists email then return tokens") {
//                coEvery { memberReader.existsByEmail(any()) } answers { true }
//
//                val result = authService.signIn(googleUser.signInRequest())
//
//                assertThat(result.accessToken).isNotBlank
//                assertThat(result.refreshToken).isNotBlank
//                coVerify(exactly = 0) { memberStore.save(googleUser.toMember()) }
//            }
//
//            it("If not exists email then register user and return tokens") {
//                coEvery { memberReader.existsByEmail(any()) } answers { false }
//                coEvery { memberStore.save(any()) } answers { googleUser.toMember() }
//
//                val result = authService.signIn(googleUser.signInRequest())
//
//                assertThat(result.accessToken).isNotBlank
//                assertThat(result.refreshToken).isNotBlank
//            }
//        }
//
//        describe("signOut") {
//            it("If accessToken is expired or invalid then throw Exception") {
//                every { jwtProvider.validateToken(any()) } answers { false }
//                assertThrows<InvalidTokenException> { authService.signOut(googleUser.signOutRequest()) }
//            }
//        }
//
//        describe("link") {
//            it("If accessToken is expired or invalid then throw Exception") {
//                every { jwtProvider.validateToken(any()) } answers { false }
//                assertThrows<InvalidTokenException> { authService.signOut(googleUser.signOutRequest()) }
//            }
//        }
//    }
//}