package com.asterlsker.auth.infrastructure.member.repository

import com.asterlsker.auth.domain.member.Member
import com.asterlsker.auth.domain.member.MemberReader
import com.asterlsker.auth.domain.model.Email
import com.asterlsker.auth.infrastructure.member.repository.query.MemberQueryRepository
import com.asterlsker.auth.infrastructure.member.repository.query.MemberSocialLoginQueryRepository
import org.springframework.stereotype.Repository

@Repository
class MemberReaderImpl(
    private val memberRepository: MemberQueryRepository,
    private val memberSocialLoginRepository: MemberSocialLoginQueryRepository
): MemberReader {

    override suspend fun existsByEmail(email: String): Boolean {
        return memberSocialLoginRepository.existsByEmail(email)
    }

    override suspend fun findByEmail(email: String): Member? {
        val result = memberRepository.findByEmail(email)
        return result?.toDomain()
    }
}