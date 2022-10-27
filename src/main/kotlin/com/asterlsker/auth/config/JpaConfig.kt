package com.asterlsker.auth.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = ["com.asterlsker.auth"])
@EntityScan(basePackages = ["com.asterlsker.auth"])
@Configuration
class JpaConfig {
}