package com.asterlsker.auth.common.response

import org.springframework.validation.Errors


class CommonResponse<T> {

    var result: Result? = null
    var data: T? = null
    var message: String? = null
    var errorCode: String? = null
    var errorFields: List<ErrorField>? = emptyList()

    enum class Result {
        SUCCESS, FAIL
    }

    companion object {
        fun success(): CommonResponse<*> {
            return success("", "")
        }

        fun <T> success(data: T): CommonResponse<T> {
            return success(data, "")
        }

        fun <T> success(data: T, message: String): CommonResponse<T> {
            return CommonResponse<T>().apply {
                result = Result.SUCCESS
                this.data = data
                this.message = message
            }
        }

        fun fail(errorCode: ErrorCode): CommonResponse<*> {
            return CommonResponse<String>().apply {
                result = Result.FAIL
                this.message = errorCode.message
                this.errorCode = errorCode.code
            }
        }

        fun fail(errorCode: ErrorCode, errors: Errors?): CommonResponse<*> {
            return CommonResponse<String>().apply {
                result = Result.FAIL
                this.message = errorCode.message
                this.errorCode = errorCode.code
                this.errorFields = ErrorField.of(errors)
            }
        }

        fun fail(message: String): CommonResponse<*> {
            return CommonResponse<String>().apply {
                result = Result.FAIL
                this.message = message
                this.errorCode = ErrorCode.BAD_REQUEST.code
            }
        }
    }
}