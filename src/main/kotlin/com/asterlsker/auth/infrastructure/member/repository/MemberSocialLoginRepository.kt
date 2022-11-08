package com.asterlsker.auth.infrastructure.member.repository

import com.asterlsker.auth.infrastructure.member.entity.MemberSocialLoginEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface MemberSocialLoginRepository: ReactiveCrudRepository<MemberSocialLoginEntity, String> {

    fun findByEmail(email: String): Mono<MemberSocialLoginEntity>?

    fun existsByEmail(email: String): Mono<Boolean>
}