package com.asterlsker.auth.common.exception

import com.asterlsker.auth.common.response.ErrorCode
import java.lang.RuntimeException

/**
 * DomainException 을 상속하는 하위 Exception 들은 도메인 예외로 범위를 한정 짓는다.
 * DDD 에서 모든 도메 로직(비지니스 로직)은 domain layer 로 한정 짓는다.
 */
open class DomainException(
    open val errorCode: ErrorCode = ErrorCode.DOMAIN
): RuntimeException(errorCode.message)