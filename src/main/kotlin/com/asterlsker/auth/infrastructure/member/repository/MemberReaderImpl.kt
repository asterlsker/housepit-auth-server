package com.asterlsker.auth.infrastructure.member.repository

import com.asterlsker.auth.common.exception.EntityException
import com.asterlsker.auth.common.response.ErrorCode
import com.asterlsker.auth.domain.member.Member
import com.asterlsker.auth.domain.member.MemberReader
import com.asterlsker.auth.domain.model.Email
import org.springframework.stereotype.Repository

@Repository
class MemberReaderImpl(
    private val memberRepository: MemberRepository,
    private val memberSocialLoginRepository: MemberSocialLoginRepository
): MemberReader {

    override fun existsByEmail(email: Email): Boolean {
        val result = memberSocialLoginRepository.existsByEmail(email.value)
        return result.block() ?: throw EntityException(ErrorCode.ENTITY_NOT_FOUND)
    }

    override fun findByEmail(email: Email): Member? {
        val result = memberRepository.findByEmail(email.value)
        return result.block()?.member?.toDomain()
    }
}