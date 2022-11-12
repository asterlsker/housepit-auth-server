package com.asterlsker.auth.infrastructure.member.repository.command

import com.asterlsker.auth.domain.member.Member


interface MemberCommandRepository {
    suspend fun save(member: Member)
}