package com.asterlsker.auth.infrastructure.member.repository

import com.asterlsker.auth.domain.member.Member
import com.asterlsker.auth.domain.member.MemberStore
import com.asterlsker.auth.infrastructure.member.repository.command.MemberCommandRepository
import org.springframework.stereotype.Repository

@Repository
class MemberStoreImpl(
    private val memberRepository: MemberCommandRepository
): MemberStore {

    override suspend fun save(member: Member) {
        memberRepository.save(member)
    }
}