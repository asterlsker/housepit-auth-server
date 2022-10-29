package com.asterlsker.auth.common.exception.domain

import com.asterlsker.auth.common.exception.DomainException
import com.asterlsker.auth.common.response.ErrorCode

class InvalidEmailException(
    override val errorCode: ErrorCode = ErrorCode.INVALID_TOKEN,
): DomainException(errorCode)