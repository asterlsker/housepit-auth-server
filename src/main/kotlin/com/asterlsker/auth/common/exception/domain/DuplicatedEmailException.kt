package com.asterlsker.auth.common.exception.domain

import com.asterlsker.auth.common.exception.DomainException
import com.asterlsker.auth.common.response.ErrorCode

class DuplicatedEmailException(
    override val errorCode: ErrorCode = ErrorCode.DUPLICATED_EMAIL
): DomainException(errorCode)