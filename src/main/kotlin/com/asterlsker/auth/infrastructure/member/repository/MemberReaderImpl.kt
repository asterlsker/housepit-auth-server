package com.asterlsker.auth.infrastructure.member.repository

import com.asterlsker.auth.common.exception.EntityException
import com.asterlsker.auth.common.response.ErrorCode
import com.asterlsker.auth.domain.member.Member
import com.asterlsker.auth.domain.member.MemberReader
import com.asterlsker.auth.domain.model.Email
import com.asterlsker.auth.domain.model.Phone
import com.asterlsker.auth.infrastructure.member.entity.MemberEntity
import org.springframework.stereotype.Repository

@Repository
class MemberReaderImpl(
    private val memberRepository: MemberRepository,
    private val memberSocialLoginRepository: MemberSocialLoginRepository
): MemberReader {
    override fun existsByEmail(email: Email): Boolean {
        val result = memberSocialLoginRepository.existsByEmail(email)
        return result.block() ?: throw EntityException(ErrorCode.ENTITY_NOT_FOUND)
    }

    override fun findByEmail(email: Email): Member? {
        // TODO 리팩토링 필요함
        val memberEntity = MemberEntity(id = "abc", userName = "Jungho", phone = Phone("01089241810"))
        return memberEntity.toDomain()
    }
}