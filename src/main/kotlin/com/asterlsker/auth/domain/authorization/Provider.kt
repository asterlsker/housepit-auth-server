package com.asterlsker.auth.domain.authorization

enum class Provider(
    val code: String
){
    GOOGLE("0"), APPLE("1"), KAKAO("2"), NAVER("3"),
    ;
}