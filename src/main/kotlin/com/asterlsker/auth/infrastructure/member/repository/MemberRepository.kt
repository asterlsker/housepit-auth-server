package com.asterlsker.auth.infrastructure.member.repository

import com.asterlsker.auth.infrastructure.member.entity.MemberEntity
import com.asterlsker.auth.infrastructure.member.repository.custom.MemberCustomRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface MemberRepository: ReactiveCrudRepository<MemberEntity, String>, MemberCustomRepository {
}