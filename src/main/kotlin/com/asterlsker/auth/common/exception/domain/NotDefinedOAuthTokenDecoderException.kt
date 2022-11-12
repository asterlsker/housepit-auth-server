package com.asterlsker.auth.common.exception.domain

import com.asterlsker.auth.common.exception.DomainException
import com.asterlsker.auth.common.response.ErrorCode

class NotDefinedOAuthTokenDecoderException(
    override val errorCode: ErrorCode = ErrorCode.NOT_DEFINED_OAUTH_TOKEN_DECODER
): DomainException(errorCode)