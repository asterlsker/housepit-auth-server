package com.asterlsker.auth.infrastructure.member.repository

import com.asterlsker.auth.common.exception.EntityException
import com.asterlsker.auth.common.response.ErrorCode
import com.asterlsker.auth.domain.member.Member
import com.asterlsker.auth.domain.member.MemberStore
import org.springframework.stereotype.Repository

@Repository
class MemberStoreImpl(
    private val memberRepository: MemberRepository
): MemberStore {

    override fun save(member: Member): Member {
        val result = memberRepository.save(member.toEntity())
        return result.block()?.toDomain() ?: throw EntityException(ErrorCode.ENTITY_SAVE)
    }
}