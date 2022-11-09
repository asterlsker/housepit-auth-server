package com.asterlsker.auth.infrastructure.member.repository.command

import com.asterlsker.auth.domain.member.Member
import com.asterlsker.auth.infrastructure.member.entity.MemberEntity
import com.linecorp.kotlinjdsl.spring.data.reactive.query.SpringDataHibernateMutinyReactiveQueryFactory
import io.smallrye.mutiny.coroutines.awaitSuspending
import org.springframework.stereotype.Repository

@Repository
class MemberCommandRepositoryImpl(
    private val queryFactory: SpringDataHibernateMutinyReactiveQueryFactory
): MemberCommandRepository {
    override suspend fun save(member: Member) {
        val entity = MemberEntity.of(member)
        queryFactory.withFactory { session, factory ->
            session.persist(entity).awaitSuspending()
            session.flush().awaitSuspending()
        }
    }
}