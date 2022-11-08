package com.asterlsker.auth.infrastructure.member.repository

import com.asterlsker.auth.infrastructure.member.entity.MemberEntity
import com.linecorp.kotlinjdsl.ReactiveQueryFactory
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class MemberRepositoryImpl(
    private val queryFactory: ReactiveQueryFactory
): MemberRepository {

    override fun findByEmail(email: String): Mono<MemberEntity> {
//        queryFactory.selectQuery<MemberEntity> {
//            select(entity(MemberEntity::class))
//        }
        return Mono.empty()
    }
}