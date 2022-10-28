package com.asterlsker.auth.infrastructure.member

import com.asterlsker.auth.domain.member.MemberReader
import org.springframework.stereotype.Component

@Component
class MemberReaderImpl(
    private val memberRepository: MemberRepository
): MemberReader {
    override fun existsByEmail(email: String): Boolean {
        return memberRepository.existsByEmail(email)
    }
}