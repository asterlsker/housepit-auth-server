package com.asterlsker.auth.domain

import com.asterlsker.auth.common.properties.JwtProperties
import com.asterlsker.auth.common.support.RedisClient
import com.asterlsker.auth.domain.authorization.token.JwtProvider
import com.asterlsker.auth.support.MemberDummy.*
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.test.TestCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtendWith

//@TestPropertySource("classpath:application-test.yml")
//@EnableConfigurationProperties(value = [JwtProperties::class])
@ExtendWith(MockKExtension::class)
internal class JwtProviderTest: DescribeSpec() {

    @InjectMockKs lateinit var jwtProvider: JwtProvider
    @MockK lateinit var objectMapper: ObjectMapper
    @MockK lateinit var jwtProperties: JwtProperties
    @MockK lateinit var redisClient: RedisClient

    companion object {
        val googleUser = GoogleUser()
    }

    override fun beforeEach(testCase: TestCase) {
        MockKAnnotations.init(this, relaxUnitFun = true)
        every { objectMapper.writeValueAsString(googleUser.getTokenIssueSpec()) } answers { "email:${googleUser.email} provider:${googleUser.provider}" }
        every { jwtProperties.token } answers { JwtProperties.TokenProperties(secretKey = "testkey", 5, 43200) }
        every { redisClient.set(any(), any(), any(), any(), any()) } answers { }
    }

    init {
        describe("issueTokens") {
            it("success issue") {
                val result = jwtProvider.issueTokens(googleUser.getTokenIssueSpec())
                assertThat(result.accessToken).isNotBlank
                assertThat(result.refreshToken).isNotBlank
            }
        }
    }
}