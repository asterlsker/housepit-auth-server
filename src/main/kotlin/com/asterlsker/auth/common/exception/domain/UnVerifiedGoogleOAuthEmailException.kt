package com.asterlsker.auth.common.exception.domain

import com.asterlsker.auth.common.exception.DomainException
import com.asterlsker.auth.common.response.ErrorCode

class UnVerifiedGoogleOAuthEmailException(
    override val errorCode: ErrorCode = ErrorCode.UN_VERIFIED_EMAIL
): DomainException()