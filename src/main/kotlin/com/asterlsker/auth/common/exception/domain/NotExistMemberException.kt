package com.asterlsker.auth.common.exception.domain

import com.asterlsker.auth.common.exception.DomainException
import com.asterlsker.auth.common.response.ErrorCode

class NotExistMemberException(
    override val errorCode: ErrorCode = ErrorCode.NOT_EXIST_MEMBER
): DomainException(errorCode)