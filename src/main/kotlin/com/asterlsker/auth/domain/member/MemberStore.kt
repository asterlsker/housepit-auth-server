package com.asterlsker.auth.domain.member


interface MemberStore {
    suspend fun save(member: Member)
}