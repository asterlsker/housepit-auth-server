package com.asterlsker.auth.infrastructure

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.ZonedDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners
abstract class BaseEntity {
    @CreatedDate
    var createdAt: ZonedDateTime = ZonedDateTime.now()
    @CreatedBy
    var createdBy: String = SYSTEM_PROPERTY_AUTHOR
    @LastModifiedDate
    var lastModifiedAt: ZonedDateTime = ZonedDateTime.now()
    @LastModifiedBy
    var lastModifiedBy: String = SYSTEM_PROPERTY_AUTHOR
}

const val SYSTEM_PROPERTY_AUTHOR = "housepit"