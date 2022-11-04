package com.asterlsker.auth.common.response

import com.asterlsker.auth.common.exception.ConvertException
import com.asterlsker.auth.common.exception.DomainException
import com.asterlsker.auth.common.exception.ValidationException
import io.grpc.Status
import net.devh.boot.grpc.server.advice.GrpcAdvice
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler
import org.apache.commons.lang3.exception.ExceptionUtils
import org.slf4j.LoggerFactory
import org.slf4j.event.Level

@GrpcAdvice
class GrpcExceptionHandlerAdvice {

    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        const val logPrefix = "# [GRPC-ERROR-LOG] "
    }

    @GrpcExceptionHandler(ConvertException::class)
    fun convertException(e: ConvertException): Status {
        printLog(e.errorCode, e)
        return Status.INVALID_ARGUMENT.withDescription(e.errorCode.getMsgWithStackTrace(e)).withCause(e)
    }

    @GrpcExceptionHandler(ValidationException::class)
    fun validationException(e: ValidationException): Status {
        printLog(e.errorCode, e)
        return Status.INVALID_ARGUMENT.withDescription(e.errorCode.getMsgWithStackTrace(e)).withCause(e)
    }

    @GrpcExceptionHandler(DomainException::class)
    fun domainException(e: DomainException): Status {
        printLog(e.errorCode, e)
        return Status.INVALID_ARGUMENT.withDescription(e.errorCode.getMsgWithStackTrace(e)).withCause(e)
    }

    @GrpcExceptionHandler(Exception::class)
    fun exception(e: Exception): Status {
        log.error("$logPrefix Not Defined or Not Handled Exception occur", e)
        return Status.INTERNAL.withDescription(ExceptionUtils.getMessage(e)).withCause(e)
    }

    private fun printLog(errorCode: ErrorCode, e: Throwable) {
        val messageTemplate = "$logPrefix ${errorCode.getMsgWithStackTrace(e)}"
        when (errorCode.logLevel) {
            Level.ERROR -> log.error(messageTemplate)
            Level.WARN -> log.warn(messageTemplate)
            Level.INFO -> log.info(messageTemplate)
            Level.DEBUG -> log.debug(messageTemplate)
            Level.TRACE -> log.trace(messageTemplate)
        }
    }
}