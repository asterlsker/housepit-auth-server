package com.asterlsker.auth.domain.member


interface MemberStore {

    fun save(member: Member): Member
}