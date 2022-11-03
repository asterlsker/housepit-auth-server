package com.asterlsker.auth.infrastructure.member.repository

import com.asterlsker.auth.domain.model.Email
import com.asterlsker.auth.infrastructure.member.entity.MemberEntity
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface MemberRepository: ReactiveCrudRepository<MemberEntity, String> {
    // TODO @Query 작성 필요 (email property 못 읽는 듯)
//    fun findByEmail(@Param("email") email: Email): Mono<MemberEntity>
}