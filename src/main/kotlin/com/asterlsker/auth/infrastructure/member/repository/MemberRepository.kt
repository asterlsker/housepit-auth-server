package com.asterlsker.auth.infrastructure.member.repository

import com.asterlsker.auth.infrastructure.member.entity.MemberEntity
import reactor.core.publisher.Mono


interface MemberRepository {
    fun findByEmail(email: String): Mono<MemberEntity>
}