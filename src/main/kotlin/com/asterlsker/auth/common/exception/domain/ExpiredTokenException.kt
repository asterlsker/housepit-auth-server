package com.asterlsker.auth.common.exception.domain

import com.asterlsker.auth.common.exception.DomainException
import com.asterlsker.auth.common.response.ErrorCode

class ExpiredTokenException(
    override val errorCode: ErrorCode = ErrorCode.EXPIRED_TOKEN
): DomainException(errorCode)