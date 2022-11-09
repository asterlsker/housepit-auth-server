import com.google.protobuf.gradle.*

val grpcVersion = "3.19.4"
val grpcKotlinVersion = "1.3.0"
val grpcProtoVersion = "1.50.1"

plugins {
    id("org.springframework.boot") version "2.7.5"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("com.google.protobuf") version "0.8.18"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
}

group = "com.asterlsker"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

springBoot {
    mainClass.value("com.asterlsker.AuthApplication")
}

repositories {
    mavenCentral()
}

dependencies {
    // starter
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("net.devh:grpc-spring-boot-starter:2.13.1.RELEASE")

    // Kotlin library
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    // just add these dependencies for use kotlin-jdsl
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    val jdslVersion = "2.0.4.RELEASE"
    implementation("com.linecorp.kotlin-jdsl:spring-data-kotlin-jdsl-starter:$jdslVersion")

    // h2 Database reactive
    implementation("com.h2database:h2")
    implementation("io.agroal:agroal-pool:2.0")
    implementation("io.vertx:vertx-jdbc-client:4.3.1")

    // redis
    implementation("org.redisson:redisson:3.17.6")

    // reactive
    implementation("com.linecorp.kotlin-jdsl:spring-data-kotlin-jdsl-hibernate-reactive:$jdslVersion")
    implementation("org.springframework.data:spring-data-commons")
    implementation("org.hibernate.reactive:hibernate-reactive-core:1.1.9.Final")
    implementation("io.smallrye.reactive:mutiny-kotlin:1.7.0")

    // Google
    implementation("com.google.guava:guava:31.1-jre")
    implementation("com.googlecode.libphonenumber:libphonenumber:8.12.57")

    // apache
    implementation("org.apache.commons:commons-lang3")

    // Annotation Processing Tool
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // GRPC
    implementation("io.grpc:grpc-protobuf:$grpcProtoVersion") // protobuf 로 만들어지는 서버 입장의 파일에서 필요한 메서드 등을 포함하고 있는 의존성
    implementation("io.grpc:grpc-kotlin-stub:1.2.0")
    compileOnly("org.apache.tomcat:annotations-api:6.0.53")
    implementation("com.google.protobuf:protobuf-kotlin:$grpcVersion") // protocol buffer 를 kotlin 파일로 컴파일하는데 사용되는 의존성

    // jwt
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("org.glassfish.jaxb:jaxb-runtime:4.0.1")

    // macos
    implementation("io.netty:netty-resolver-dns-native-macos:4.1.79.Final:osx-aarch_64")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.2")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.0")
    testImplementation("com.ninja-squad:springmockk:3.1.1")
    testImplementation("io.mockk:mockk:1.12.3")
    testImplementation("io.kotest:kotest-runner-junit5:5.1.0")
    testImplementation("io.kotest:kotest-assertions-core:5.1.0")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

sourceSets.main.configure {
    resources.srcDirs("src/main/resources/common", "src/main/resources/config")
}

// build 이후에 Stub 클래스가 생성되는 directory 에 대해서 target 으로 추가하기 위한 설정.
// 해당 설정을 통해 소스 내에서 Stub 클래스 참조 가능
sourceSets {
    getByName("main") {
        java {
            srcDirs(
                "build/generated/source/proto/main/java",
                "build/generated/source/proto/main/kotlin",
                "build/generated/source/proto/main/grpc",
                "build/generated/source/proto/main/grpckt",
            )
        }
    }
}

// build 시점에 protobuf 생성
protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$grpcVersion"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:$grpcProtoVersion"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:$grpcKotlinVersion:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
            it.builtins {
                id("kotlin")
            }
        }
    }
}

tasks.jar { enabled = false }
tasks.bootJar { enabled = true }