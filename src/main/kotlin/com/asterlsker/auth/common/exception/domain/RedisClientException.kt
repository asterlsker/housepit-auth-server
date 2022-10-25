package com.asterlsker.auth.common.exception.domain

import com.asterlsker.auth.common.response.ErrorCode

class RedisClientException(
    val errorCode: ErrorCode = ErrorCode.REDIS_CLIENT,
): RuntimeException(errorCode.message)