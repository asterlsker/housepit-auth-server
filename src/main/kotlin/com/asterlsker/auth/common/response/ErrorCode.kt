package com.asterlsker.auth.common.response

import org.slf4j.event.Level
import org.springframework.http.HttpStatus


enum class ErrorCode(
    val status: HttpStatus,
    val code: String,
    val message: String,
    val logLevel: Level = Level.INFO
) {
    // Well known Errors
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "W1004", "Bad Request", Level.ERROR),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "W1005", "Internal Server Error", Level.ERROR),

    // Custom Errors
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "C1002", "Invalid Token"),
    INVALID_ACCESS_TOKEN(HttpStatus.BAD_REQUEST, "C1003", "Invalid Access Token"),
    INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "C1004", "Invalid Refresh Token"),
    REDIS_CLIENT(HttpStatus.BAD_REQUEST, "C1005", "Redis Client Error", Level.ERROR),
    DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "C1006", "Duplicated Email"),
    NOT_EXIST_MEMBER(HttpStatus.BAD_REQUEST, "C1007", "Not Exists Member"),
    NOT_FOUND_PROVIDER(HttpStatus.BAD_REQUEST, "C1008", "Not Found Provider"),
    INVALID_PHONE(HttpStatus.BAD_REQUEST, "C1009", "Invalid Phone"),
    EXIST_MEMBER(HttpStatus.BAD_REQUEST, "C1010", "Exists Member"),
    INVALID_EMAIL(HttpStatus.BAD_REQUEST, "C1011", "Invalid Email"),
    ENTITY_NOT_FOUND(HttpStatus.BAD_REQUEST, "C1012", "Entity Not Found"),
    ENTITY_SAVE(HttpStatus.BAD_REQUEST, "C1013", "Entity Save Error"),

    // Hierarchy
    DOMAIN(HttpStatus.BAD_REQUEST, "H1001", "Domain Layer Error"),
    CONVERT(HttpStatus.BAD_REQUEST, "H1002", "Convert Error"),
    VALIDATION(HttpStatus.BAD_REQUEST, "H1003", "Validation Error"),
    ENTITY(HttpStatus.BAD_REQUEST, "H1004", "Entity Error"),
    ;
}