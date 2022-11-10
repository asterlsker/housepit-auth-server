package com.asterlsker.auth.infrastructure.member.repository.query

import com.asterlsker.auth.infrastructure.member.entity.MemberSocialLoginEntity
import com.linecorp.kotlinjdsl.querydsl.expression.column
import com.linecorp.kotlinjdsl.spring.data.reactive.query.SpringDataHibernateMutinyReactiveQueryFactory
import com.linecorp.kotlinjdsl.spring.data.reactive.query.listQuery
import org.springframework.stereotype.Repository

@Repository
class MemberSocialLoginQueryRepositoryImpl(
    private val queryFactory: SpringDataHibernateMutinyReactiveQueryFactory
): MemberSocialLoginQueryRepository {

    override suspend fun existsByEmail(email: String): Boolean {
        val result = queryFactory.listQuery<MemberSocialLoginEntity> {
            select(MemberSocialLoginEntity::class.java)
            from(entity(MemberSocialLoginEntity::class))
            where(
                column(MemberSocialLoginEntity::email).equal(email)
            )
        }
        return result.isNotEmpty()
    }
}