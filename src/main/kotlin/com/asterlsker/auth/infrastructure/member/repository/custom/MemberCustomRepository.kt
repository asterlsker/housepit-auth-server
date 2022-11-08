package com.asterlsker.auth.infrastructure.member.repository.custom

import com.asterlsker.auth.infrastructure.member.entity.MemberEntity
import reactor.core.publisher.Mono

interface MemberCustomRepository {

    fun findByEmail(email: String): Mono<MemberEntity>
}