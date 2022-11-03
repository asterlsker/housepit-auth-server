package com.asterlsker.auth

import com.asterlsker.auth.interfaces.AuthGrpcServer
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan("com.asterlsker.auth.common.properties")
@SpringBootApplication
class AuthApplication

fun main(args: Array<String>) {
//    val grpcServer = AuthGrpcServer("12345")
//    grpcServer.start()
//    grpcServer.blockUntilShutdown()
    runApplication<AuthApplication>(*args) {
        this.webApplicationType = WebApplicationType.REACTIVE
    }
}
