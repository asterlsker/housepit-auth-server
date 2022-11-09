package com.asterlsker.auth.infrastructure.member.repository.query

import com.asterlsker.auth.infrastructure.member.entity.MemberEntity
import com.asterlsker.auth.infrastructure.member.entity.MemberSocialLoginEntity
import com.linecorp.kotlinjdsl.querydsl.expression.column
import com.linecorp.kotlinjdsl.querydsl.from.fetch
import com.linecorp.kotlinjdsl.spring.data.reactive.query.SpringDataHibernateMutinyReactiveQueryFactory
import com.linecorp.kotlinjdsl.spring.data.reactive.query.singleQuery
import org.springframework.stereotype.Repository

@Repository
class MemberSocialLoginQueryRepositoryImpl(
    private val queryFactory: SpringDataHibernateMutinyReactiveQueryFactory
): MemberSocialLoginQueryRepository {

    override suspend fun existsByEmail(email: String): Boolean {
        return queryFactory.singleQuery {
            selectMulti(entity(MemberEntity::class))
            from(entity(MemberSocialLoginEntity::class))
            fetch(MemberSocialLoginEntity::member)
            where(column(MemberSocialLoginEntity::email).equal(email))
        }
    }
}