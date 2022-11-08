package com.asterlsker.auth.infrastructure.member.repository

import reactor.core.publisher.Mono

interface MemberSocialLoginRepository {

    fun existsByEmail(email: String): Mono<Boolean>
}