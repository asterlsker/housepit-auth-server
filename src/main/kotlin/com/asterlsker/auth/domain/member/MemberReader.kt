package com.asterlsker.auth.domain.member

interface MemberReader {

    suspend fun existsByEmail(email: String): Boolean

    suspend fun findByEmail(email: String): Member?

    suspend fun findByMemberUuid(memberUuid: String): Member?
}