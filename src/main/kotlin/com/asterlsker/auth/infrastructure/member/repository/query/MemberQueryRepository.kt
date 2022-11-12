package com.asterlsker.auth.infrastructure.member.repository.query

import com.asterlsker.auth.infrastructure.member.entity.MemberEntity


interface MemberQueryRepository {
    suspend fun findByEmail(email: String): MemberEntity?
}