package com.asterlsker.auth.config

import io.r2dbc.h2.H2ConnectionConfiguration
import io.r2dbc.h2.H2ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories


@Configuration
@EnableR2dbcRepositories(basePackages = ["com.asterlsker.auth.infrastructure.*.repository"])
@EnableR2dbcAuditing
class R2dbcConfig {

//    @Bean
//    override fun connectionFactory(): H2ConnectionFactory {
//        return H2ConnectionFactory(
//            H2ConnectionConfiguration.builder()
//                .url("mem:authdb;DB_CLOSE_DELAY=-1;")
//                .username("sa")
//                .build()
//        )
//    }
}