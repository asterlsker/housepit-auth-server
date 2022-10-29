package com.asterlsker.auth.infrastructure.member.repository

import com.asterlsker.auth.domain.model.Email
import com.asterlsker.auth.infrastructure.member.entity.MemberSocialLoginEntity
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface MemberSocialLoginRepository: ReactiveCrudRepository<MemberSocialLoginEntity, String> {

    fun existsByEmail(@Param("email") email: Email): Mono<Boolean>
}