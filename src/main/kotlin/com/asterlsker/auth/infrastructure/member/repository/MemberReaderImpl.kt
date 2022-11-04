package com.asterlsker.auth.infrastructure.member.repository

import com.asterlsker.auth.common.exception.EntityException
import com.asterlsker.auth.common.response.ErrorCode
import com.asterlsker.auth.domain.member.Member
import com.asterlsker.auth.domain.member.MemberReader
import com.asterlsker.auth.domain.model.Email
import com.asterlsker.auth.domain.model.Phone
import com.asterlsker.auth.infrastructure.member.entity.MemberEntity
import org.springframework.stereotype.Repository

@Repository
class MemberReaderImpl(
    private val memberRepository: MemberRepository,
    private val memberSocialLoginRepository: MemberSocialLoginRepository
): MemberReader {

    /**
     * TODO 이슈 해결 필요
     * io.grpc.StatusException: UNKNOWN: BadSqlGrammarException: executeMany; bad SQL grammar [SELECT member_social_login.id FROM member_social_login WHERE member_social_login.email = $1 LIMIT 1]; nested exception is io.r2dbc.spi.R2dbcBadGrammarException: [42104] [42S04] Table "MEMBER_SOCIAL_LOGIN" not found (this database is empty); SQL statement:
    SELECT member_social_login.id FROM member_social_login WHERE member_social_login.email = $1 LIMIT 1 [42104-214]
     */
    override fun existsByEmail(email: Email): Boolean {
        val result = memberSocialLoginRepository.existsByEmail(email.value)
        val re = result.block()
        return re ?: throw EntityException(ErrorCode.ENTITY_NOT_FOUND)
    }

    override fun findByEmail(email: Email): Member? {
        // TODO 리팩토링 필요함
        val memberEntity = MemberEntity(id = "abc", userName = "Jungho", phone = Phone("01089241810"))
        return memberEntity.toDomain()
    }
}