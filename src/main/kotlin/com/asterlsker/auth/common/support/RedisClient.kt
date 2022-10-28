package com.asterlsker.auth.common.support

import com.asterlsker.auth.common.exception.domain.RedisClientException
import com.fasterxml.jackson.databind.ObjectMapper
import org.redisson.Redisson
import org.redisson.api.RBucket
import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.slf4j.LoggerFactory
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.annotation.PreDestroy

@Component
class RedisClient private constructor(
    private val objectMapper: ObjectMapper
) {

    private val log = LoggerFactory.getLogger(javaClass)
    val configFile = "redisson.yml"
    lateinit var client: RedissonClient

    init {
        val resource = ClassPathResource(configFile)
        try {
            val config: Config = Config.fromYAML(resource.inputStream)
            client = Redisson.create(config)
        } catch (e: IOException) {
            log.error("# RedisClient Constructor failed : ", e)
        }
    }

    @PreDestroy
    fun destroy() {
        client.shutdown()

        for (i in 1..2) {
            try {
                log.info("# Redisson instance was started to be shutdown")
                Thread.sleep(1000L)
            } catch (e: Exception) {
                log.error("# RedisClient destroy error: $e")
            }

            if (this.client.isShutdown) {
                log.info("# Redisson instance has been shut down!")
                break
            }
        }
    }

    fun getLock(lockId: String): RLock {
        return client.getLock(lockId)
    }

    fun <T> get(cacheName: String, key: String, classType: Class<T>): T? {
        val bucket: RBucket<String> = getBucket(cacheName, key)

        return bucket.get()?.let {
            try {
                objectMapper.readValue(it, classType)
            } catch (e: Exception) {
                log.error("# RedisClient get error: $e")
                throw RedisClientException()
            }
        }
    }

    fun set(cacheName: String, key: String, value: Any, expireTime: Long, timeUnit: TimeUnit) {
        val bucket: RBucket<String> = getBucket(cacheName, key)

        try {
            val convertedValue = objectMapper.writeValueAsString(value)
            log.info("# RedisClient cache value: $convertedValue")
            bucket.set(convertedValue, expireTime, timeUnit)
        } catch (e: Exception) {
            log.error("# RedisClient set error: $e")
            throw RedisClientException()
        }
    }

    private fun <T> getBucket(cacheName: String, key: String): RBucket<T> {
        return client.getBucket(bucketName(cacheName, key))
    }

    private fun bucketName(cacheName: String, key: String): String {
        return "$cacheName:$key"
    }
}