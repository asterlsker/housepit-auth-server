package com.asterlsker.auth.infrastructure.member.repository

import com.asterlsker.auth.infrastructure.member.entity.MemberSocialLoginEntity
import com.linecorp.kotlinjdsl.ReactiveQueryFactory
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class MemberSocialLoginRepositoryImpl(
    private val queryFactory: ReactiveQueryFactory
): MemberSocialLoginRepository {

    override fun existsByEmail(email: String): Mono<Boolean> {
        TODO("Not yet implemented")
    }
}