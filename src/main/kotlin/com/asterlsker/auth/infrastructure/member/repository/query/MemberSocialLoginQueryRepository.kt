package com.asterlsker.auth.infrastructure.member.repository.query


interface MemberSocialLoginQueryRepository {

    suspend fun existsByEmail(email: String): Boolean
}